package com.example.data.python

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.withTimeoutOrNull
import org.json.JSONObject
import java.io.File
import java.util.UUID

data class PythonErrorDiagnostic(
    val errorType: String,
    val errorLine: Int,
    val rawMessage: String,
    val explanation: String,
    val tip: String,
    val codeSnippet: String = ""
)

data class ExecutionResult(
    val isSuccess: Boolean,
    val stdout: String,
    val rawError: String? = null,
    val friendlyError: String? = null,
    val errorLine: Int = -1,
    val diagnostic: PythonErrorDiagnostic? = null,
    val executionTimeMs: Long = 0,
    val isTimedOut: Boolean = false
)

class PythonEngine(private val context: Context) {

    private val mainHandler = Handler(Looper.getMainLooper())
    private var webView: WebView? = null
    private var isReady = false
    private val readyDeferred = CompletableDeferred<Boolean>()
    private val pendingExecutions = mutableMapOf<String, ExecutionRequest>()

    private data class ExecutionRequest(
        val code: String,
        val deferred: CompletableDeferred<ExecutionResult>
    )

    init {
        mainHandler.post {
            setupWebView()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        if (webView != null) return
        try {
            val cacheBase = context.cacheDir
            File(cacheBase, "WebView/Default/HTTP Cache/Code Cache/js").mkdirs()
            File(cacheBase, "WebView/Default/HTTP Cache/Code Cache/wasm").mkdirs()
        } catch (_: Exception) {}

        try {
            val wv = WebView(context).apply {
                setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    cacheMode = WebSettings.LOAD_NO_CACHE
                }
                addJavascriptInterface(AndroidBridge(), "AndroidBridge")
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                    }
                }
            }
            webView = wv
            wv.loadUrl("file:///android_asset/python/runner.html")
        } catch (e: Exception) {
            android.util.Log.w("PythonEngine", "WebView initialization: ${e.message}")
            isReady = true
        }
    }

    suspend fun executeCode(code: String, timeoutMs: Long = 7000L): ExecutionResult {
        // Ensure engine is loaded
        if (!isReady) {
            val readyResult = withTimeoutOrNull(4000L) {
                readyDeferred.await()
            }
            if (readyResult != true) {
                isReady = true
            }
        }

        val execId = UUID.randomUUID().toString()
        val deferred = CompletableDeferred<ExecutionResult>()
        pendingExecutions[execId] = ExecutionRequest(code, deferred)

        mainHandler.post {
            try {
                val escapedCode = JSONObject.quote(code)
                val js = "runPythonCode($escapedCode, '$execId');"
                webView?.evaluateJavascript(js, null)
            } catch (e: Exception) {
                pendingExecutions.remove(execId)
                val diagnostic = buildDiagnostic(
                    rawError = e.message ?: "Erro interno",
                    lineNum = -1,
                    code = code
                )
                deferred.complete(
                    ExecutionResult(
                        isSuccess = false,
                        stdout = "",
                        rawError = e.message,
                        friendlyError = diagnostic.explanation,
                        diagnostic = diagnostic
                    )
                )
            }
        }

        val result = withTimeoutOrNull(timeoutMs) {
            deferred.await()
        }

        pendingExecutions.remove(execId)

        return result ?: run {
            val diagnostic = PythonErrorDiagnostic(
                errorType = "TimeoutError",
                errorLine = -1,
                rawMessage = "Tempo limite de execução excedido (7 segundos)",
                explanation = "O programa levou mais de 7 segundos para responder e foi interrompido por segurança.",
                tip = "Verifique se você criou um laço 'while' ou 'for' sem condição de parada (loop infinito) ou que não incrementa a variável de controle.",
                codeSnippet = ""
            )
            ExecutionResult(
                isSuccess = false,
                stdout = "",
                rawError = "Timeout: execução excedeu o limite.",
                friendlyError = diagnostic.explanation,
                diagnostic = diagnostic,
                isTimedOut = true
            )
        }
    }

    fun destroy() {
        mainHandler.post {
            webView?.apply {
                stopLoading()
                clearHistory()
                clearCache(true)
                loadUrl("about:blank")
                destroy()
            }
            webView = null
        }
    }

    private inner class AndroidBridge {
        @JavascriptInterface
        fun onEngineReady() {
            isReady = true
            if (!readyDeferred.isCompleted) {
                readyDeferred.complete(true)
            }
        }

        @JavascriptInterface
        fun onOutputChunk(execId: String, text: String) {
            // Streaming outputs if needed
        }

        @JavascriptInterface
        fun onExecutionSuccess(execId: String, stdout: String, elapsedMs: Long) {
            val req = pendingExecutions.remove(execId)
            req?.deferred?.complete(
                ExecutionResult(
                    isSuccess = true,
                    stdout = stdout.trimEnd(),
                    executionTimeMs = elapsedMs
                )
            )
        }

        @JavascriptInterface
        fun onExecutionError(
            execId: String,
            errorStr: String,
            lineNum: Int,
            stdout: String,
            elapsedMs: Long
        ) {
            val req = pendingExecutions.remove(execId)
            val code = req?.code ?: ""
            val diagnostic = buildDiagnostic(errorStr, lineNum, code)

            req?.deferred?.complete(
                ExecutionResult(
                    isSuccess = false,
                    stdout = stdout.trimEnd(),
                    rawError = errorStr,
                    friendlyError = diagnostic.explanation,
                    errorLine = diagnostic.errorLine,
                    diagnostic = diagnostic,
                    executionTimeMs = elapsedMs
                )
            )
        }
    }

    companion object {
        fun buildDiagnostic(rawError: String, lineNum: Int, code: String): PythonErrorDiagnostic {
            val lines = code.split("\n")
            val cleanLine = if (lineNum in 1..lines.size) lineNum else -1
            val snippet = if (cleanLine != -1 && cleanLine - 1 < lines.size) {
                lines[cleanLine - 1].trim()
            } else ""

            return when {
                rawError.contains("SyntaxError") -> {
                    PythonErrorDiagnostic(
                        errorType = "SyntaxError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "O Python encontrou um caractere inesperado ou uma estrutura incompleta que quebra as regras da linguagem.",
                        tip = "Verifique se esqueceu de fechar aspas (\" ou '), parênteses ( ) ou colocar dois pontos (:) no fim de if, elif, else, for, while, def ou class.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("IndentationError") -> {
                    PythonErrorDiagnostic(
                        errorType = "IndentationError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "O espaçamento no início da linha está desalinhado. O Python usa a indentação (4 espaços) para saber o que está dentro de blocos.",
                        tip = "Use a tecla 'Tab (4 esp.)' da barra rápida para manter 4 espaços dentro de funções, condicionais ou laços.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("ZeroDivisionError") -> {
                    PythonErrorDiagnostic(
                        errorType = "ZeroDivisionError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "Tentativa de divisão por zero. Matematicamente e computacionalmente, não é possível dividir por 0.",
                        tip = "Antes de dividir, verifique se a variável divisora não é igual a 0 ou adicione um teste 'if divisor != 0:'.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("NameError") -> {
                    val match = Regex("name '(.+)' is not defined").find(rawError)
                    val varName = match?.groupValues?.getOrNull(1) ?: "informada"
                    PythonErrorDiagnostic(
                        errorType = "NameError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "O nome '$varName' não foi reconhecido pelo interpretador.",
                        tip = "Verifique se a variável ou função '$varName' foi digitada corretamente (letras maiúsculas/minúsculas importam!) ou se você a declarou antes dessa linha.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("TypeError") -> {
                    PythonErrorDiagnostic(
                        errorType = "TypeError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "Operação executada entre tipos de dados incompatíveis.",
                        tip = "Por exemplo, você não pode somar texto com número diretamente: use str(numero) para converter em texto ou int(texto) para número.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("IndexError") -> {
                    PythonErrorDiagnostic(
                        errorType = "IndexError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "Você tentou acessar uma posição que ultrapassa o tamanho da lista ou string.",
                        tip = "Lembre-se: em Python, a contagem de índices começa em 0. Uma lista com 3 itens tem posições 0, 1 e 2. Use len(lista) para checar o tamanho.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("KeyError") -> {
                    PythonErrorDiagnostic(
                        errorType = "KeyError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "A chave solicitada não existe dentro do dicionário.",
                        tip = "Verifique o nome da chave ou use o método seguro dicionario.get('chave', valor_padrao) para evitar que o código quebre.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("ValueError") -> {
                    PythonErrorDiagnostic(
                        errorType = "ValueError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "A função recebeu um valor com tipo correto, mas conteúdo inválido para a conversão esperada.",
                        tip = "Por exemplo: int(\"abc\") falha porque as letras 'abc' não podem ser convertidas em número inteiro.",
                        codeSnippet = snippet
                    )
                }

                rawError.contains("AttributeError") -> {
                    PythonErrorDiagnostic(
                        errorType = "AttributeError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "Você tentou acessar um método ou atributo que não existe para esse objeto.",
                        tip = "Verifique a ortografia do método (ex: listas usam .append(), não .add()) e se o objeto é do tipo esperado.",
                        codeSnippet = snippet
                    )
                }

                else -> {
                    PythonErrorDiagnostic(
                        errorType = "RuntimeError",
                        errorLine = cleanLine,
                        rawMessage = rawError,
                        explanation = "Ocorreu um erro durante a execução do programa.",
                        tip = "Analise a mensagem de erro detalhada e revise os passos lógicos do seu algoritmo.",
                        codeSnippet = snippet
                    )
                }
            }
        }
    }
}

package com.example.data.python

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático para o Nível Básico de Python (Aulas py_b1_l1 até py_b3_l2).
 * Estrutura: Matéria (Fundamentos) -> Sintaxe e Funções (O que cada função faz) -> Exemplos Comentados Passo a Passo.
 */
object PythonBasicDidactic {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_b1_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Um programa de computador é uma sequência estruturada de instruções executadas por um interpretador. Em Python, o interpretador lê o código linha por linha, de cima para baixo.
                    
                    Para que um programa se comunique com o usuário ou informe o resultado de seus cálculos e processamentos, ele precisa enviar dados para a 'Saída Padrão' (conhecida tecnicamente como stdout, representada pela tela do terminal ou console).
                    
                    Textos literais em Python são chamados de strings (cadeias de caracteres) e precisam estar envolvidos por aspas simples ('...') ou duplas ("..."). Se você esquecer as aspas, o Python tentará interpretar as palavras como nomes de variáveis ou comandos e gerará um erro de sintaxe (SyntaxError).
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "print()",
                        syntax = "print(*objects, sep=' ', end='\\n')",
                        description = "Envia a representação textual de um ou múltiplos objetos para a saída padrão (console). Converte automaticamente cada argumento para texto antes de exibir.",
                        parametersInfo = "*objects: valores ou variáveis a exibir; sep: caractere separador entre múltiplos valores (padrão espaço ' '); end: caractere final adicionado após o último valor (padrão quebra de linha '\\n')."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Strings Literais",
                        syntax = "'Texto' ou \"Texto\"",
                        description = "Representam sequências de caracteres alfanuméricos e símbolos. Aspas simples e duplas têm exatamente o mesmo efeito em Python.",
                        parametersInfo = "Use aspas duplas se o texto contiver apóstrofo (ex: \"d'água\"), ou use barra invertida de escape (ex: 'd\\'água')."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo 1: Impressão Simples e Quebra de Linha",
                        codeOrFormula = "print('Olá, Mundo!')\nprint('Aprendendo Python no Knowverse')",
                        explanation = """
                            Linha 1: O interpretador avalia a string 'Olá, Mundo!' e a exibe no console. Por padrão, adiciona uma quebra de linha ao final.
                            Linha 2: O segundo print é exibido na linha seguinte no terminal.
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo 2: Múltiplos Argumentos com Separador Personalizado",
                        codeOrFormula = "print('Python', 3, 'Inovador', sep=' - ')",
                        explanation = """
                            Linha 1: O print recebe 3 argumentos de tipos diferentes (duas strings e um número inteiro 3).
                            O parâmetro sep=' - ' instrui o Python a separar cada item por um traço ao invés do espaço tradicional.
                            Saída no terminal: Python - 3 - Inovador
                        """.trimIndent()
                    )
                ),
                goldTip = "Cuidado com letras maiúsculas e minúsculas: Python é Case-Sensitive! Escrever Print() com 'P' maiúsculo gerará um NameError!"
            )

            "py_b1_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Variáveis são nomes simbólicos que funcionam como 'etiquetas' ou referências apontando para espaços alocados na memória RAM do computador onde os dados estão armazenados.
                    
                    Ao contrário de linguagens como C ou Java, Python possui TIPAGEM DINÂMICA: você não precisa declarar se a variável é do tipo inteiro, texto ou decimal antes de criá-la. O próprio interpretador descobre o tipo com base no valor atribuído no momento da execução!
                    
                    Além disso, Python tem TIPAGEM FORTE: ele nunca tentará converter magicamente tipos incompatíveis sem que você peça explicitamente (por exemplo, somar texto com número gerará um TypeError ao invés de concatenar de forma imprevisível).
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Operador de Atribuição =",
                        syntax = "nome_variavel = valor",
                        description = "Cria a variável na memória e associa o valor à direita ao identificador à esquerda.",
                        parametersInfo = "Nomes de variáveis devem começar com letra ou underscore (_), não podem conter espaços nem começar com números."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "type()",
                        syntax = "type(objeto)",
                        description = "Função de introspecção nativa que retorna a classe/tipo de dado do objeto passado como argumento.",
                        parametersInfo = "Retornos comuns: <class 'int'> (inteiros), <class 'float'> (decimais), <class 'str'> (textos), <class 'bool'> (booleanos)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Tipos Primitivos Fundamentais",
                        syntax = "int (inteiros) | float (decimais) | str (textos) | bool (True/False)",
                        description = "Os 4 blocos de construção essenciais para armazenar qualquer valor elementar em memória.",
                        parametersInfo = "Booleanos sempre começam com letra MAIÚSCULA: True e False."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Criando e Verificando Tipos de Variáveis",
                        codeOrFormula = "linguagem = 'Python'\nversao = 3\naltura = 1.75\nativo = True\nprint(type(linguagem))\nprint(type(versao))\nprint(type(altura))\nprint(type(ativo))",
                        explanation = """
                            Linhas 1-4: Alocam variáveis dos tipos str, int, float e bool.
                            Linhas 5-8: type() inspeciona o tipo de cada objeto em tempo de execução.
                        """.trimIndent()
                    )
                ),
                goldTip = "Em Python adota-se a convenção snake_case para nomes de variáveis (letras minúsculas separadas por sublinhado, ex: total_alunos, preco_medio)!"
            )

            "py_b1_l3" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Muitas vezes, dados chegam ao nosso programa no formato de texto (por exemplo, quando lidos de um formulário web, arquivo texto ou entrada do usuário), mesmo que representem números como "25" ou "3.14".
                    
                    Se tentarmos somar o texto "10" com o número 5, o Python gerará um erro grave de tipo (TypeError: can only concatenate str to str).
                    
                    Para resolver isso, utilizamos o TYPE CASTING (ou conversão explícita de tipos), chamando os construtores das classes desejadas para reinterpretar os bytes na memória.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "int()",
                        syntax = "int(valor[, base])",
                        description = "Converte uma string contendo dígitos ou um float para número inteiro (descarta casas decimais por truncamento).",
                        parametersInfo = "int('42') -> 42; int(9.99) -> 9. Se a string contiver letras, lança ValueError."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "float()",
                        syntax = "float(valor)",
                        description = "Converte números inteiros ou strings formatadas para números de ponto flutuante com casas decimais.",
                        parametersInfo = "Use ponto (.) como separador decimal em Python, nunca vírgula (ex: float('3.14'))."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "str()",
                        syntax = "str(valor)",
                        description = "Converte qualquer objeto Python (números, listas, booleanos) na sua representação textual em string.",
                        parametersInfo = "str(100) -> '100'; str(True) -> 'True'."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Conversão e Operação Aritmética",
                        codeOrFormula = "dado_texto = '50'\nnumero = int(dado_texto)\ntotal = numero + 10\nprint('Total calculado:', total)",
                        explanation = """
                            Linha 1: '50' é uma string de 2 caracteres.
                            Linha 2: int('50') converte a string para o inteiro numérico 50.
                            Linha 3: Agora podemos somar 50 + 10 = 60 com segurança!
                        """.trimIndent()
                    )
                ),
                goldTip = "Ao converter números com casas decimais a partir de texto, chame sempre float() antes de int(): int('3.14') gera erro, mas int(float('3.14')) funciona perfeitamente!"
            )

            "py_b2_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Tomar decisões com base em condições é o que transforma uma simples calculadora estática em um software inteligente e dinâmico.
                    
                    As estruturas condicionais (if, elif, else) avaliam expressões lógicas: se a expressão for avaliada como VERDADEIRA (True), o interpretador executa o bloco interno; caso contrário, salta para o próximo ramo.
                    
                    A INDENTAÇÃO É OBRIGATÓRIA: Em Python, não usamos chaves ({ }) para delimitar blocos de código como em C ou Java. O Python usa a indentação (4 espaços à direita) para saber exatamente quais comandos pertencem ao if!
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Estrutura if / elif / else",
                        syntax = "if condicao_1:\n    bloco_1\nelif condicao_2:\n    bloco_2\nelse:\n    bloco_padrao",
                        description = "Executa seletivamente um dos blocos de código dependendo de qual condição booleana for satisfeita primeiro.",
                        parametersInfo = "elif é a contração de 'else if'. O bloco else é opcional e só roda se nenhuma das condições anteriores for atendida."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Operadores Relacionais de Comparação",
                        syntax = "== (igual) | != (diferente) | > (maior) | < (menor) | >= (maior/igual) | <= (menor/igual)",
                        description = "Comparam valores e retornam sempre um booleano puro (True ou False).",
                        parametersInfo = "CUIDADO: '==' testa igualdade lógica, enquanto '=' realiza atribuição de variável!"
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Classificador de Notas",
                        codeOrFormula = "nota = 8.5\nif nota >= 9.0:\n    print('Excelente')\nelif nota >= 7.0:\n    print('Aprovado')\nelse:\n    print('Recuperação')",
                        explanation = """
                            Linha 1: nota recebe 8.5.
                            Linha 2: nota >= 9.0 é False (8.5 não é maior que 9).
                            Linha 4: nota >= 7.0 é True! O interpretador entra no bloco e executa print('Aprovado').
                            Linha 6: O else é ignorado porque a condição elif já foi satisfeita.
                        """.trimIndent()
                    )
                ),
                goldTip = "Nunca esqueça os dois-pontos (:) ao final das linhas que iniciam if, elif e else!"
            )

            "py_b2_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Na vida real e nos sistemas modernos, as regras de negócio raramente dependem de um único critério isolado. Frequentemente precisamos verificar se o usuário é maior de idade E tem carteira de motorista, OU se é domingo OU feriado.
                    
                    Os OPERADORES LÓGICOS booleanos permitem combinar múltiplas comparações em uma única expressão de decisão composta.
                    
                    Em Python, ao contrário de outras linguagens que usam símbolos estranhos como && ou ||, os operadores lógicos são escritos em inglês claro: and, or e not!
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Operador 'and' (E lógico)",
                        syntax = "expressao_a and expressao_b",
                        description = "Retorna True somente se AMBAS as expressões forem verdadeiras ao mesmo tempo. Se uma for falsa, todo o resultado é False.",
                        parametersInfo = "Avaliação em curto-circuito: se o lado esquerdo for False, o Python nem avalia o lado direito!"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Operador 'or' (OU lógico)",
                        syntax = "expressao_a or expressao_b",
                        description = "Retorna True se PELO MENOS UMA das expressões for verdadeira. Só retorna False se ambas forem falsas.",
                        parametersInfo = "Se o lado esquerdo for True, o resultado é imediatamente True."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Operador 'not' (Negação lógica)",
                        syntax = "not expressao",
                        description = "Inverte o valor booleano: transforma True em False e False em True.",
                        parametersInfo = "Útil para checar ausência, vazio ou estados desativados."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Validação de Acesso Bancário",
                        codeOrFormula = "saldo = 1500\ncartao_ativo = True\nvalor_saque = 400\n\nif cartao_ativo and saldo >= valor_saque:\n    saldo -= valor_saque\n    print('Saque autorizado! Novo saldo:', saldo)\nelse:\n    print('Transação recusada')",
                        explanation = """
                            A condição (cartao_ativo and saldo >= valor_saque) testa duas coisas:
                            1. cartao_ativo é True.
                            2. 1500 >= 400 também é True.
                            Como ambas são True, o operador 'and' resulta em True e o saque é liberado!
                        """.trimIndent()
                    )
                ),
                goldTip = "Use parênteses para agrupar expressões compostas complexas: (a and b) or c é muito mais legível e evita ambiguidades de precedência!"
            )

            "py_b3_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Computadores são incomparáveis na realização de tarefas repetitivas sem cansaço ou erro. Para repetir comandos em programação, utilizamos laços de repetição (loops).
                    
                    O laço 'for' em Python é um iterador poderoso: ele percorre sequencialmente cada item de uma sequência (como números, listas ou textos).
                    
                    Para gerar sequências numéricas de contagem de maneira extremamente rápida e econômica em memória, usamos a função integrada range().
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "range()",
                        syntax = "range(start, stop[, step])",
                        description = "Gera uma sequência imutável de números inteiros do início até stop - 1. O limite final stop é sempre EXCLUSIVO!",
                        parametersInfo = "start: valor inicial (padrão 0); stop: limite superior (não incluído!); step: passo/incremento (padrão 1)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Laço for ... in",
                        syntax = "for variavel in iteravel:\n    bloco_repeticao",
                        description = "Atribui sucessivamente cada elemento do iterável à variável e executa o bloco indentado.",
                        parametersInfo = "Ao final de cada iteração, o laço avança automaticamente para o próximo elemento até esgotar a sequência."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Contagem com range(1, 6)",
                        codeOrFormula = "for i in range(1, 6):\n    print('Passo número:', i)",
                        explanation = """
                            O range(1, 6) gera os números 1, 2, 3, 4, 5 (o 6 não entra!).
                            Na 1ª iteração: i = 1, imprime 'Passo número: 1'.
                            Na 2ª iteração: i = 2, imprime 'Passo número: 2'.
                            ... até i = 5, totalizando 5 repetições controladas.
                        """.trimIndent()
                    )
                ),
                goldTip = "Se quiser fazer uma contagem regressiva, use um passo negativo: range(10, 0, -1) vai de 10 até 1!"
            )

            "py_b3_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Enquanto o laço 'for' é perfeito quando você já sabe de antemão quantas vezes deseja repetir (ou sobre qual sequência iterar), o laço 'while' é projetado para repetir ENQUANTO uma condição permanecer verdadeira!
                    
                    Pense em um jogo: o jogo continua rodando enquanto vidas > 0 for verdade. No segundo em que as vidas acabam, o laço se encerra.
                    
                    • CUIDADO COM O LOOP INFINITO: Se a condição nunca se tornar falsa dentro do bloco, o programa travará em execução perpétua! Sempre certifique-se de atualizar a variável de controle a cada volta.
                    
                    • COMANDOS DE CONTROLE:
                      - break: interrompe o laço imediatamente e pula para fora.
                      - continue: pula o restante da volta atual e vai direto para a próxima iteração.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Laço while",
                        syntax = "while condicao:\n    bloco_comandos\n    atualizacao_variavel",
                        description = "Testa a condição antes de cada volta: se for True, executa o bloco; se for False, encerra o laço.",
                        parametersInfo = "A condição é reavaliada no início de cada ciclo."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "break e continue",
                        syntax = "break (abortar laço)  |  continue (pular para próxima volta)",
                        description = "Instruções especiais de controle de fluxo para encerrar precocemente ou pular iterações.",
                        parametersInfo = "break é muito usado em loops infinitos controlados do tipo while True: if cond: break."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Acumulador com while e break",
                        codeOrFormula = "contador = 1\nsoma = 0\n\nwhile contador <= 5:\n    soma += contador\n    contador += 1\n\nprint('Soma acumulada de 1 a 5:', soma)",
                        explanation = """
                            Volta 1: contador=1, soma=0+1=1, contador vira 2.
                            Volta 2: contador=2, soma=1+2=3, contador vira 3.
                            Volta 3: contador=3, soma=3+3=6, contador vira 4.
                            Volta 4: contador=4, soma=6+4=10, contador vira 5.
                            Volta 5: contador=5, soma=10+5=15, contador vira 6.
                            Volta 6: contador <= 5 é 6 <= 5 (False!). O laço termina e imprime 15.
                        """.trimIndent()
                    )
                ),
                goldTip = "Em todo loop while, nunca se esqueça de incrementar a variável de controle (ex: contador += 1) para evitar que o código trave em loop infinito!"
            )

            else -> null
        }
    }
}

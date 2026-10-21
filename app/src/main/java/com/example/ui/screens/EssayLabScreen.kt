package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.essay.EnemEssayService
import com.example.data.essay.EssayCorrectionResult
import com.example.data.local.UserProgressRepository
import com.example.ui.components.StructuredMarkdownRenderer
import com.example.ui.theme.BorderGlow
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.PrimaryVioletLight
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.WarningAmber
import kotlinx.coroutines.launch

// Temas oficiais das edições recentes do ENEM
private val TEMAS_OFICIAIS_ENEM = listOf(
    "Desafios para a valorização da herança africana no Brasil",
    "Desafios para o enfrentamento da invisibilidade do trabalho de cuidado realizado pela mulher no Brasil",
    "Desafios para a valorização de comunidades e povos tradicionais no Brasil",
    "Invisibilidade e registro civil: garantia de acesso à cidadania no Brasil",
    "O estigma associado às doenças mentais na sociedade brasileira",
    "Democratização do acesso ao cinema no Brasil"
)

// Exemplo padrão de dissertação argumentativa no modelo ENEM para teste rápido
private const val REDACAO_EXEMPLO_PADRAO = """Na obra modernista "Vidas Secas", de Graciliano Ramos, retrata-se a trajetória de Fabiano e sua família, sujeitos invisibilizados e desprovidos de direitos fundamentais em meio ao sertão nordestino. Fora da ficção, essa conjuntura de vulnerabilidade reverbera intensamente na sociedade contemporânea, evidenciando graves entraves estruturais. Desse modo, torna-se imperioso analisar não apenas a inércia estatal, mas também a negligência social que perpetuam esse cenário deplorável.

Em primeiro plano, cabe pontuar a omissão governamental como propulsora da problemática. De acordo com o filósofo Thomas Hobbes, em sua teoria do Contrato Social, cabe ao Estado garantir o bem-estar e a seguridade dos cidadãos. Todavia, no Brasil hodierno, constata-se uma atuação insuficiente dos poderes públicos na implementação de políticas redistributivas e de amparo efetivo. Em decorrência dessa lacuna de gestão, parcelas significativas da população permanecem marginalizadas do acesso pleno à cidadania e aos serviços básicos.

Ademais, a indiferença coletiva corrobora a perpetuação dessa injustiça. Segundo a filósofa Hannah Arendt, em seu conceito de "Banalidade do Mal", a normalização de situações desumanas decorre da ausência de reflexão crítica coletiva. Sob essa ótica, a sociedade brasileira naturaliza as disparidades e silencia as demandas de grupos historicamente vulneráveis, convertendo mazelas estruturais em fatos corriqueiros do cotidiano. Assim, a falta de empatia e engajamento cívico compromete a coesão social.

Portanto, medidas são urgentes para mitigar tais disparidades. Cabe ao Governo Federal, por meio de parceria entre o Ministério da Educação e o Ministério dos Direitos Humanos, criar um Plano Nacional de Conscientização e Inclusão Social, mediante a realização de campanhas educativas em redes de ampla difusão e palestras em escolas públicas. Essa ação deve detalhar os mecanismos de denúncia e acolhimento comunitário, com a finalidade de desconstruir a apatia social e restabelecer a dignidade civil conforme preconiza a Constituição Cidadã de 1988."""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssayLabScreen(
    progressRepository: UserProgressRepository? = null,
    onNavigateBack: () -> Unit
) {
    BackHandler(onBack = onNavigateBack)

    var topic by remember { mutableStateOf(TEMAS_OFICIAIS_ENEM.first()) }
    var essayText by remember { mutableStateOf(REDACAO_EXEMPLO_PADRAO) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var correctionResult by remember { mutableStateOf<EssayCorrectionResult?>(null) }
    var showApiKeyDialog by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    // Cálculo dinâmico de métricas da redação
    val lineCount = remember(essayText) {
        if (essayText.isBlank()) 0
        else {
            val explicitLines = essayText.lines().count { it.isNotBlank() }
            // Estimativa de quebra de linha visual para o limite de 30 linhas do ENEM (aprox. 85 chars por linha de folha)
            val wrappedLines = essayText.lines().sumOf { line ->
                val len = line.trim().length
                if (len == 0) 0 else ((len - 1) / 80) + 1
            }
            wrappedLines.coerceAtLeast(explicitLines)
        }
    }
    val wordCount = remember(essayText) {
        if (essayText.isBlank()) 0
        else essayText.split(Regex("\\s+")).filter { it.isNotBlank() }.size
    }
    val charCount = remember(essayText) { essayText.length }

    Scaffold(
        containerColor = DeepNavy,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SurfaceElevated)
                            .border(1.dp, BorderSubtle, CircleShape)
                            .testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = TextPrimary
                        )
                    }

                    Column {
                        Text(
                            text = "Laboratório de Redação",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Correção via Matriz de Competências INEP",
                            style = MaterialTheme.typography.labelSmall,
                            color = PrimaryVioletLight
                        )
                    }
                }

                Surface(
                    color = ObsidianSurface,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Grade,
                            contentDescription = null,
                            tint = GoldXp,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Nota Máx. 1000",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                }
            }

            // Banner explicativo do módulo
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(PrimaryViolet.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Avaliação Pedagógica ENEM",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Escreva sua dissertação respeitando a estrutura de até 30 linhas e envie para análise das 5 competências do INEP.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Seção 1: Tema da Redação
            Text(
                text = "1. TEMA DA REDAÇÃO",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryVioletLight,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            OutlinedTextField(
                value = topic,
                onValueChange = { topic = it },
                label = { Text("Tema ou Proposta de Redação", color = TextSecondary) },
                placeholder = { Text("Digite ou escolha o tema da dissertação...", color = TextTertiary) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("essay_topic_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = ObsidianSurface,
                    unfocusedContainerColor = ObsidianSurface,
                    focusedBorderColor = PrimaryViolet,
                    unfocusedBorderColor = BorderSubtle,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                shape = RoundedCornerShape(10.dp),
                maxLines = 3
            )

            // Seletor de Temas Oficiais do ENEM
            Text(
                text = "Temas oficiais recentes para treino rápido:",
                style = MaterialTheme.typography.labelSmall,
                color = TextTertiary,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TEMAS_OFICIAIS_ENEM.forEach { suggestion ->
                    val isSelected = topic == suggestion
                    Surface(
                        color = if (isSelected) PrimaryViolet.copy(alpha = 0.2f) else SurfaceCard,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (isSelected) PrimaryViolet else BorderSubtle,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { topic = suggestion }
                    ) {
                        Text(
                            text = suggestion.take(38) + if (suggestion.length > 38) "..." else "",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSelected) TextPrimary else TextSecondary,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Seção 2: Textarea da Redação com contador
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "2. TEXTO DA DISSERTAÇÃO",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryVioletLight,
                    letterSpacing = 1.sp
                )

                // Botão para carregar exemplo ou limpar
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Exemplo Modelo",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldXp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable {
                                essayText = REDACAO_EXEMPLO_PADRAO
                            }
                            .padding(4.dp)
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextTertiary
                    )
                    Text(
                        text = "Limpar",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextTertiary,
                        modifier = Modifier
                            .clickable {
                                essayText = ""
                            }
                            .padding(4.dp)
                    )
                }
            }

            // Metadados da folha do ENEM (Linhas: max 30)
            Surface(
                color = ObsidianSurface,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = if (lineCount in 7..30) SuccessEmerald.copy(alpha = 0.2f) else WarningAmber.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "Linhas: $lineCount/30",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (lineCount in 7..30) SuccessEmerald else WarningAmber,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        if (lineCount < 7) {
                            Text(
                                text = "(Mín. 7 linhas)",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextTertiary
                            )
                        } else if (lineCount > 30) {
                            Text(
                                text = "(Limite: 30 linhas)",
                                style = MaterialTheme.typography.labelSmall,
                                color = WarningAmber
                            )
                        }
                    }

                    Text(
                        text = "$wordCount palavras • $charCount caracteres",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
            }

            // Campo de Texto da Redação
            OutlinedTextField(
                value = essayText,
                onValueChange = { essayText = it },
                placeholder = {
                    Text(
                        text = "Digite seu texto dissertativo-argumentativo em prosa...\n\n- Introdução com tese clara e repertório legitimado\n- Desenvolvimento 1 com argumento consistente\n- Desenvolvimento 2 com aprofundamento crítico\n- Conclusão com proposta de intervenção completa (Agente, Ação, Modo, Efeito, Detalhe)",
                        color = TextTertiary,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .testTag("essay_text_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = ObsidianSurface,
                    unfocusedContainerColor = ObsidianSurface,
                    focusedBorderColor = PrimaryViolet,
                    unfocusedBorderColor = BorderSubtle,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 22.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )

            // Mensagem de erro caso ocorra
            errorMessage?.let { err ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .border(1.dp, Color(0xFFEF4444), RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ErrorOutline,
                            contentDescription = null,
                            tint = Color(0xFFEF4444)
                        )
                        Text(
                            text = err,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão Principal: "Corrigir Redação"
            Button(
                onClick = {
                    if (topic.isBlank()) {
                        errorMessage = "Por favor, especifique o tema da redação."
                        return@Button
                    }
                    if (essayText.isBlank() || essayText.length < 50) {
                        errorMessage = "A redação precisa conter texto suficiente para análise pedagógica (mínimo de 7 linhas/50 caracteres)."
                        return@Button
                    }

                    errorMessage = null
                    isLoading = true

                    coroutineScope.launch {
                        val result = EnemEssayService.correctEssay(
                            topic = topic,
                            essayText = essayText
                        )
                        isLoading = false
                        result.onSuccess { corrected ->
                            correctionResult = corrected
                            // Premia o estudante com XP por treinar redação
                            progressRepository?.addBonusXp(50)
                        }.onFailure { ex ->
                            errorMessage = ex.message ?: "Ocorreu um erro ao comunicar com a IA."
                        }
                    }
                },
                enabled = !isLoading && essayText.isNotBlank() && topic.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("correct_essay_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryViolet,
                    disabledContainerColor = SurfaceElevated
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Avaliando pela Matriz do INEP...",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = TextPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Corrigir Redação",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }

            // Barra de progresso visual adicional durante o loading
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = PrimaryVioletLight,
                        trackColor = BorderSubtle
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Analisando norma culta, repertório, projeto de texto e proposta de intervenção...",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextTertiary
                    )
                }
            }

            // Seção de Resultados Estruturados (Markdown + Tabela + Diagnóstico)
            correctionResult?.let { res ->
                Spacer(modifier = Modifier.height(24.dp))

                // Card de Nota Final
                Card(
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderGlow, RoundedCornerShape(14.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "NOTA ESTIMADA",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = PrimaryVioletLight,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = "${res.totalScore}",
                                        style = MaterialTheme.typography.headlineLarge,
                                        fontWeight = FontWeight.Black,
                                        color = if (res.totalScore >= 800) SuccessEmerald else GoldXp,
                                        fontSize = 38.sp
                                    )
                                    Text(
                                        text = "/ 1000",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = TextSecondary,
                                        modifier = Modifier.padding(bottom = 6.dp)
                                    )
                                }
                            }

                            // Selo de Desempenho
                            Surface(
                                color = if (res.totalScore >= 800) SuccessEmerald.copy(alpha = 0.15f) else GoldXp.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.border(
                                    1.dp,
                                    if (res.totalScore >= 800) SuccessEmerald else GoldXp,
                                    RoundedCornerShape(8.dp)
                                )
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = if (res.totalScore >= 800) SuccessEmerald else GoldXp,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = when {
                                            res.totalScore >= 900 -> "Excelente (Faixa 5)"
                                            res.totalScore >= 800 -> "Muito Bom (Faixa 4)"
                                            res.totalScore >= 640 -> "Adequado (Faixa 3)"
                                            else -> "Precisa Praticar"
                                        },
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                }
                            }
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = BorderSubtle
                        )

                        // 5 Competências Cards
                        Text(
                            text = "Matriz das 5 Competências:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        res.competencies.forEach { comp ->
                            Surface(
                                color = SurfaceCard,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = comp.title,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = comp.description,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = TextTertiary,
                                            fontSize = 11.sp
                                        )
                                    }

                                    Surface(
                                        color = if (comp.score >= 160) SuccessEmerald.copy(alpha = 0.2f) else PrimaryViolet.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = "${comp.score} / 200",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (comp.score >= 160) SuccessEmerald else PrimaryVioletLight,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Detalhamento e Diagnóstico Pedagógico em Markdown
                Card(
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = PrimaryVioletLight,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Diagnóstico Pedagógico Completo",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }

                        // Renderizador Markdown estruturado
                        StructuredMarkdownRenderer(
                            markdownText = res.rawMarkdown
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // AVISO OBRIGATÓRIO DE RODAPÉ (Exigência estrita do usuário)
            Surface(
                color = SurfaceElevated,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Aviso legal",
                        tint = PrimaryVioletLight,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Atenção: A nota gerada é uma estimativa pedagógica por Inteligência Artificial para fins de treino.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.enem.EnemFirebaseManager
import com.example.data.enem.EnemQuestion
import com.example.data.enem.EnemQuestionRepository
import com.example.data.exercise.ExerciseDiscipline
import com.example.data.exercise.UniversalExercise
import com.example.data.exercise.UniversalExerciseBank
import com.example.data.exercise.VisualTable
import com.example.data.local.UserProgressRepository
import com.example.ui.components.GeoGebraMathGraphView
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.MathFormattedText
import com.example.ui.components.TopUserStatsBar
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CodeTextStyle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Converte a representação textual de dificuldade em peso numérico (1 a 3) para comparação algorítmica.
 */
fun getDifficultyRank(diff: String): Int {
    return when (diff.lowercase().trim()) {
        "fácil", "facil", "básico", "basico", "iniciante" -> 1
        "médio", "medio", "mediano", "intermediário", "intermediario" -> 2
        "difícil", "dificil", "avançado", "avancado", "expert" -> 3
        else -> 2
    }
}

/**
 * Algoritmo de Transição Adaptativa de Dificuldade:
 * - Condição de Acerto: seleciona próxima questão de dificuldade IGUAL ou SUPERIOR (preferindo mesmo tópico ou disciplina).
 * - Condição de Erro: seleciona próxima questão de dificuldade MAIS FÁCIL dentro do mesmo tópico/subtópico.
 */
fun findNextAdaptiveExercise(
    current: UniversalExercise,
    allExercises: List<UniversalExercise>,
    answeredIds: Set<String>,
    wasCorrect: Boolean
): UniversalExercise? {
    val currentLevel = getDifficultyRank(current.difficulty)

    if (wasCorrect) {
        // Alvo: Dificuldade IGUAL ou SUPERIOR (rank >= currentLevel)
        // 1. Mesmo subtópico, rank >= currentLevel, não respondida
        val sameTopicHigherUnanswered = allExercises.filter {
            it.id != current.id &&
            it.subtopic.equals(current.subtopic, ignoreCase = true) &&
            getDifficultyRank(it.difficulty) >= currentLevel &&
            it.id !in answeredIds
        }
        if (sameTopicHigherUnanswered.isNotEmpty()) {
            return sameTopicHigherUnanswered.first()
        }

        // 2. Mesma disciplina, rank >= currentLevel, não respondida
        val sameDisciplineHigherUnanswered = allExercises.filter {
            it.id != current.id &&
            it.discipline == current.discipline &&
            getDifficultyRank(it.difficulty) >= currentLevel &&
            it.id !in answeredIds
        }
        if (sameDisciplineHigherUnanswered.isNotEmpty()) {
            return sameDisciplineHigherUnanswered.first()
        }

        // 3. Qualquer disciplina no banco com rank >= currentLevel, não respondida
        val globalHigherUnanswered = allExercises.filter {
            it.id != current.id &&
            getDifficultyRank(it.difficulty) >= currentLevel &&
            it.id !in answeredIds
        }
        if (globalHigherUnanswered.isNotEmpty()) {
            return globalHigherUnanswered.first()
        }

        // 4. Fallback dentro da mesma disciplina
        val sameDisciplineFallback = allExercises.filter {
            it.id != current.id &&
            it.discipline == current.discipline &&
            getDifficultyRank(it.difficulty) >= currentLevel
        }
        if (sameDisciplineFallback.isNotEmpty()) {
            return sameDisciplineFallback.first()
        }

        return allExercises.firstOrNull { it.id != current.id && it.id !in answeredIds }
            ?: allExercises.firstOrNull { it.id != current.id }
    } else {
        // Alvo: MAIS FÁCIL (rank < currentLevel) dentro do mesmo tópico
        val targetLevel = maxOf(1, currentLevel - 1)

        // 1. Mesmo subtópico com rank menor ou igual ao nível alvo
        val sameSubtopicEasier = allExercises.filter {
            it.id != current.id &&
            it.subtopic.equals(current.subtopic, ignoreCase = true) &&
            getDifficultyRank(it.difficulty) <= targetLevel &&
            it.id !in answeredIds
        }
        if (sameSubtopicEasier.isNotEmpty()) {
            return sameSubtopicEasier.first()
        }

        // 2. Mesma disciplina com rank mais fácil
        val sameDisciplineEasier = allExercises.filter {
            it.id != current.id &&
            it.discipline == current.discipline &&
            getDifficultyRank(it.difficulty) <= targetLevel &&
            it.id !in answeredIds
        }
        if (sameDisciplineEasier.isNotEmpty()) {
            return sameDisciplineEasier.first()
        }

        // 3. Qualquer questão de reforço mais fácil
        val globalEasier = allExercises.filter {
            it.id != current.id &&
            getDifficultyRank(it.difficulty) <= targetLevel
        }
        if (globalEasier.isNotEmpty()) {
            return globalEasier.first()
        }

        return allExercises.firstOrNull { it.id != current.id }
    }
}

@Composable
fun ExerciseSolverScreen(
    progressRepository: UserProgressRepository,
    userPhotoUrl: String? = null,
    onNavigateToEssayLab: (() -> Unit)? = null,
    isFocusModeActive: Boolean = false,
    onFocusModeChange: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val userProgress by progressRepository.progressState.collectAsState()

    val context = LocalContext.current
    val enemRepo = remember { EnemQuestionRepository.getInstance(context) }
    val enemFirebaseManager = remember { EnemFirebaseManager.getInstance(context, progressRepository) }
    val answeredEnemMap by enemFirebaseManager.answeredQuestions.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedDiscipline by remember { mutableStateOf(ExerciseDiscipline.ALL) }
    var selectedSubtopic by remember { mutableStateOf("all") }
    var selectedDifficulty by remember { mutableStateOf("all") }
    var selectedYear by remember { mutableStateOf<Int?>(null) }
    var onlyVisuals by remember { mutableStateOf(false) }
    var showAdvancedFilters by remember { mutableStateOf(false) }

    // Estado do Modo Foco (Tela Cheia)
    var activeFocusExercise by remember { mutableStateOf<UniversalExercise?>(null) }
    var focusStreak by remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    // Carrega e registra questões do ENEM no banco universal imediatamente
    val enemExercises = remember {
        val list = enemRepo.getAsUniversalExercises()
        UniversalExerciseBank.registerEnemExercises(list)
        list
    }

    // Respostas locais e resolvidas na sessão
    val userAnswers = remember { mutableStateMapOf<String, Int>() }
    val solvedExercises = remember { mutableStateMapOf<String, Boolean>() }

    // Sincroniza histórico do ENEM
    LaunchedEffect(answeredEnemMap) {
        answeredEnemMap.forEach { (qId, submission) ->
            if (userAnswers[qId] == null) {
                userAnswers[qId] = when {
                    submission.selectedAlternative.startsWith("A") -> 0
                    submission.selectedAlternative.startsWith("B") -> 1
                    submission.selectedAlternative.startsWith("C") -> 2
                    submission.selectedAlternative.startsWith("D") -> 3
                    submission.selectedAlternative.startsWith("E") -> 4
                    else -> 0
                }
            }
            if (submission.isCorrect) {
                solvedExercises[qId] = true
            }
        }
    }

    // Opções dinâmicas de subtópicos por disciplina
    val subtopicOptions = remember(selectedDiscipline) {
        when (selectedDiscipline) {
            ExerciseDiscipline.ENEM -> listOf(
                "all" to "Todos os Tópicos ENEM",
                "Matemática Básica" to "Matemática Básica",
                "Matemática Financeira" to "Mat. Financeira",
                "Estatística" to "Estatística",
                "Geometria Plana" to "Geometria Plana",
                "Geometria Espacial" to "Geometria Espacial",
                "Funções" to "Funções",
                "Álgebra e Equações" to "Álgebra e Equações",
                "Análise Combinatória" to "Combinatória",
                "Probabilidade" to "Probabilidade",
                "Trigonometria" to "Trigonometria"
            )
            ExerciseDiscipline.PROGRAMMING -> listOf(
                "all" to "Todos os Tópicos",
                "if e else (Condicionais)" to "if e else (Condicionais)",
                "for & while (Laços)" to "for & while (Laços)",
                "Listas, Tuplas & Fatiamento" to "Listas e Fatiamento",
                "Dicionários & Sets" to "Dicionários e Sets",
                "Funções & Lambda" to "Funções e Lambda",
                "Classes & POO" to "Classes & POO",
                "Tratamento de Exceções" to "Tratamento de Exceções"
            )
            ExerciseDiscipline.BASIC_MATH -> listOf(
                "all" to "Todos os Tópicos",
                "Geometria Analítica & Gráficos" to "GeoGebra & Gráficos",
                "Geometria: Áreas e Volumes" to "Geometria e Volumes",
                "Frações e Decimais" to "Frações e Decimais",
                "MMC e MDC" to "MMC e MDC",
                "Potenciação e Radiciação" to "Potenciação e Radiciação",
                "Notação Científica" to "Notação Científica",
                "Conversão de Unidades" to "Conversão de Unidades",
                "Razão e Proporção" to "Razão e Proporção",
                "Regra de Três (Simples e Composta)" to "Regra de Três",
                "Escalas Cartográficas" to "Escalas",
                "Porcentagem e Variação" to "Porcentagem",
                "Equações e Sistemas Lineares" to "Equações e Sistemas",
                "Estatística: Média, Mediana e Moda" to "Estatística (ENEM)"
            )
            ExerciseDiscipline.CALCULUS -> listOf(
                "all" to "Todos os Tópicos",
                "Geometria Analítica & Gráficos" to "GeoGebra Gráficos",
                "Domínio & Gráficos" to "Domínio e Gráficos",
                "Limites & Continuidade" to "Limites e Continuidade",
                "Assíntotas & Infinito" to "Assíntotas e Infinito",
                "Derivadas & Retas Tangentes" to "Derivadas e Tangente",
                "Regra da Cadeia" to "Regra da Cadeia",
                "Taxas Relacionadas" to "Taxas Relacionadas",
                "Otimização" to "Otimização",
                "Integrais & TFC" to "Integrais e TFC"
            )
            ExerciseDiscipline.ALL -> listOf(
                "all" to "Todos os Tópicos",
                "Matemática Básica" to "ENEM: Mat. Básica",
                "Estatística" to "ENEM: Estatística",
                "Geometria Plana" to "ENEM: Geometria",
                "Geometria Analítica & Gráficos" to "GeoGebra & Gráficos",
                "if e else (Condicionais)" to "Python: if/else",
                "for & while (Laços)" to "Python: Loops",
                "Classes & POO" to "Python: POO",
                "Limites & Continuidade" to "Cálculo: Limites",
                "Derivadas & Retas Tangentes" to "Cálculo: Derivadas",
                "Integrais & TFC" to "Cálculo: Integrais"
            )
        }
    }

    val allExercises = remember(enemExercises) { UniversalExerciseBank.getAllExercises() }

    val filteredList = remember(
        searchQuery,
        selectedDiscipline,
        selectedSubtopic,
        selectedDifficulty,
        selectedYear,
        onlyVisuals,
        enemExercises
    ) {
        val baseList = UniversalExerciseBank.filter(
            discipline = selectedDiscipline,
            subtopic = selectedSubtopic,
            difficulty = selectedDifficulty,
            query = searchQuery,
            onlyWithVisuals = onlyVisuals
        )
        if (selectedYear != null && selectedYear != 0) {
            baseList.filter {
                it.category.contains(selectedYear.toString()) ||
                it.source.contains(selectedYear.toString()) ||
                it.id.contains(selectedYear.toString())
            }
        } else {
            baseList
        }
    }

    // Notifica o container principal quando o modo foco está ativo
    LaunchedEffect(activeFocusExercise) {
        onFocusModeChange?.invoke(activeFocusExercise != null)
    }

    // BackHandler para sair do Modo Foco se estiver ativo
    BackHandler(enabled = activeFocusExercise != null) {
        activeFocusExercise = null
        onFocusModeChange?.invoke(false)
    }

    // ========================================================
    // MODO FOCO (TELA CHEIA) ATIVO
    // ========================================================
    if (activeFocusExercise != null) {
        val exercise = activeFocusExercise!!
        val selectedOption = userAnswers[exercise.id]
        val isSolved = solvedExercises[exercise.id] == true

        FocusModeExerciseScreen(
            exercise = exercise,
            focusStreak = focusStreak,
            onCloseFocusMode = {
                activeFocusExercise = null
                onFocusModeChange?.invoke(false)
            },
            onSelectOption = { optIndex ->
                userAnswers[exercise.id] = optIndex
                val isCorrect = optIndex == exercise.correctIndex

                if (isCorrect) {
                    focusStreak += 1
                    if (!isSolved) {
                        solvedExercises[exercise.id] = true
                        progressRepository.recordCheckIn()
                        progressRepository.addBonusXp(exercise.xpReward)
                    }
                } else {
                    focusStreak = 0
                }

                // Registra telemetria no Firebase se for ENEM
                if (exercise.category.contains("ENEM", ignoreCase = true) ||
                    exercise.source.contains("ENEM", ignoreCase = true) ||
                    exercise.id.startsWith("enem_")
                ) {
                    val altLetter = ('A' + optIndex).toString()
                    val enemQ = enemRepo.getQuestionById(exercise.id) ?: EnemQuestion(
                        id = exercise.id,
                        year = 2023,
                        topic = exercise.category,
                        subtopic = exercise.subtopic,
                        title = exercise.problemStatement,
                        alternatives = exercise.options,
                        correctAnswer = ('A' + exercise.correctIndex).toString()
                    )
                    enemFirebaseManager.recordAnswer(
                        question = enemQ,
                        selectedAlternative = altLetter,
                        isCorrect = isCorrect
                    )
                }
            },
            onNextQuestion = { wasCorrect ->
                val answeredSet = userAnswers.keys.toSet()
                val nextAdaptive = findNextAdaptiveExercise(
                    current = exercise,
                    allExercises = allExercises,
                    answeredIds = answeredSet,
                    wasCorrect = wasCorrect
                )
                if (nextAdaptive != null) {
                    activeFocusExercise = nextAdaptive
                }
            }
        )
        return
    }

    // ========================================================
    // VISÃO DE LISTA DE EXERCÍCIOS COM FILTROS MINIMALISTAS
    // Cabeçalho de título excluído conforme instrução (área vermelha removida)
    // ========================================================
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
    ) {
        // Top Stats Bar (Perfil e XP)
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = userPhotoUrl
        )

        // Painel de Filtros Minimalista (Estilo Estuda.com)
        Surface(
            color = ObsidianSurface,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = BorderSubtle)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                // 1. Barra de Busca Simples
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = "Pesquisar por assunto, código, fórmula ou termo...",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextTertiary, fontSize = 12.sp)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = PrimaryViolet,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Limpar busca",
                                    tint = TextSecondary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SurfaceElevated,
                        unfocusedContainerColor = SurfaceElevated,
                        focusedBorderColor = PrimaryViolet,
                        unfocusedBorderColor = BorderSubtle,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("exercise_search_input")
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 2. Menus Suspensos Minimalistas (Disciplina e Tópico)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Dropdown Disciplina
                    MinimalDropdown(
                        label = "Matéria",
                        selectedValue = selectedDiscipline.label,
                        options = listOf(
                            ExerciseDiscipline.ALL.name to "Todas as Matérias",
                            ExerciseDiscipline.ENEM.name to "Banco ENEM Oficial",
                            ExerciseDiscipline.BASIC_MATH.name to "Matemática Básica",
                            ExerciseDiscipline.CALCULUS.name to "Cálculo I",
                            ExerciseDiscipline.PROGRAMMING.name to "Python (Lógica)"
                        ),
                        onSelect = { key ->
                            selectedDiscipline = ExerciseDiscipline.valueOf(key)
                            selectedSubtopic = "all"
                        },
                        modifier = Modifier.weight(1f)
                    )

                    // Dropdown Tópico
                    val currentTopicLabel = subtopicOptions.find { it.first == selectedSubtopic }?.second ?: "Todos os Tópicos"
                    MinimalDropdown(
                        label = "Tópico",
                        selectedValue = currentTopicLabel,
                        options = subtopicOptions,
                        onSelect = { key -> selectedSubtopic = key },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 3. Botão Expansível "Filtros avançados"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showAdvancedFilters = !showAdvancedFilters }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (showAdvancedFilters) "Ocultar filtros avançados" else "Filtros avançados",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PrimaryViolet,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${filteredList.size} questões",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GoldXp,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = if (showAdvancedFilters) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Opções de Filtros Avançados Expansíveis
                AnimatedVisibility(
                    visible = showAdvancedFilters,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Dificuldade
                            val diffLabel = when (selectedDifficulty) {
                                "Fácil" -> "Fácil"
                                "Mediano" -> "Médio"
                                "Difícil" -> "Difícil"
                                else -> "Todas as Dificuldades"
                            }
                            MinimalDropdown(
                                label = "Dificuldade",
                                selectedValue = diffLabel,
                                options = listOf(
                                    "all" to "Todas as Dificuldades",
                                    "Fácil" to "Fácil",
                                    "Mediano" to "Médio",
                                    "Difícil" to "Difícil"
                                ),
                                onSelect = { selectedDifficulty = it },
                                modifier = Modifier.weight(1f)
                            )

                            // Ano (se houver ENEM)
                            val yrLabel = if (selectedYear == null) "Todos os Anos" else "ENEM $selectedYear"
                            MinimalDropdown(
                                label = "Edição",
                                selectedValue = yrLabel,
                                options = listOf(
                                    "0" to "Todos os Anos",
                                    "2024" to "ENEM 2024",
                                    "2023" to "ENEM 2023",
                                    "2022" to "ENEM 2022",
                                    "2021" to "ENEM 2021"
                                ),
                                onSelect = { key ->
                                    selectedYear = if (key == "0") null else key.toIntOrNull()
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }

                        // Toggle para recursos visuais + Limpar filtros
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Surface(
                                color = if (onlyVisuals) SuccessEmerald.copy(alpha = 0.18f) else SurfaceCard,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (onlyVisuals) SuccessEmerald else BorderSubtle
                                ),
                                modifier = Modifier.clickable { onlyVisuals = !onlyVisuals }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Image,
                                        contentDescription = null,
                                        tint = if (onlyVisuals) SuccessEmerald else TextTertiary,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Apenas com recursos visuais/gráficos",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (onlyVisuals) SuccessEmerald else TextSecondary,
                                            fontSize = 10.sp
                                        )
                                    )
                                }
                            }

                            if (selectedDiscipline != ExerciseDiscipline.ALL || selectedSubtopic != "all" || selectedDifficulty != "all" || selectedYear != null || onlyVisuals || searchQuery.isNotEmpty()) {
                                Text(
                                    text = "Limpar filtros",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = ErrorRose,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    ),
                                    modifier = Modifier
                                        .clickable {
                                            searchQuery = ""
                                            selectedDiscipline = ExerciseDiscipline.ALL
                                            selectedSubtopic = "all"
                                            selectedDifficulty = "all"
                                            selectedYear = null
                                            onlyVisuals = false
                                        }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // ========================================================
        // LISTA DE QUESTÕES (Cards compactos com clique para Modo Foco)
        // ========================================================
        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null,
                        tint = TextTertiary,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Nenhuma questão encontrada com esses filtros.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            searchQuery = ""
                            selectedDiscipline = ExerciseDiscipline.ALL
                            selectedSubtopic = "all"
                            selectedDifficulty = "all"
                            selectedYear = null
                            onlyVisuals = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("Resetar Filtros", fontSize = 12.sp)
                    }
                }
            }
        } else {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(filteredList, key = { _, item -> item.id }) { index, item ->
                    val isSolved = solvedExercises[item.id] == true

                    ExerciseListItemCard(
                        item = item,
                        indexInList = index,
                        isSolved = isSolved,
                        onClick = {
                            activeFocusExercise = item
                            onFocusModeChange?.invoke(true)
                        }
                    )
                }
            }
        }
    }
}

/**
 * Dropdown Minimalista em Jetpack Compose com suporte ao tema escuro.
 */
@Composable
private fun MinimalDropdown(
    label: String,
    selectedValue: String,
    options: List<Pair<String, String>>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Surface(
            color = SurfaceElevated,
            shape = RoundedCornerShape(6.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TextTertiary,
                            fontSize = 9.sp
                        )
                    )
                    Text(
                        text = selectedValue,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.5.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(ObsidianSurface)
                .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
        ) {
            options.forEach { (key, display) ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = display,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = if (selectedValue == display) PrimaryViolet else TextPrimary,
                                fontWeight = if (selectedValue == display) FontWeight.Bold else FontWeight.Normal
                            )
                        )
                    },
                    onClick = {
                        onSelect(key)
                        expanded = false
                    }
                )
            }
        }
    }
}

/**
 * Card compacto da lista de exercícios que convida para o Modo Foco (Tela Cheia).
 */
@Composable
private fun ExerciseListItemCard(
    item: UniversalExercise,
    indexInList: Int,
    isSolved: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val diffColor = when (item.difficulty) {
        "Fácil" -> SuccessEmerald
        "Mediano" -> GoldXp
        "Difícil" -> ErrorRose
        else -> TextSecondary
    }

    val disciplineBadgeColor = when (item.discipline) {
        ExerciseDiscipline.PROGRAMMING -> PrimaryViolet
        ExerciseDiscipline.BASIC_MATH -> SuccessEmerald
        ExerciseDiscipline.CALCULUS -> GoldXp
        ExerciseDiscipline.ENEM -> Color(0xFFF59E0B)
        ExerciseDiscipline.ALL -> PrimaryViolet
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = if (isSolved) 1.dp else 1.dp,
                color = if (isSolved) SuccessEmerald.copy(alpha = 0.5f) else BorderSubtle,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .testTag("exercise_card_${item.id}")
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Linha de cabeçalho: Número + Categoria + Nível + Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "#${indexInList + 1}",
                            style = CodeTextStyle.copy(
                                color = GoldXp,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }

                    Surface(
                        color = disciplineBadgeColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = item.category,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = disciplineBadgeColor,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Surface(
                        color = diffColor.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = item.difficulty,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = diffColor,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                if (isSolved) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Resolvido",
                            tint = SuccessEmerald,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Resolvido",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SuccessEmerald,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        )
                    }
                } else {
                    Text(
                        text = "+${item.xpReward} XP",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldXp,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Subtópico
            Text(
                text = item.subtopic,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Trecho do Enunciado
            Text(
                text = item.problemStatement,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 11.5.sp,
                    lineHeight = 16.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botão/Ação de Abrir em Tela Cheia
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextTertiary,
                        fontSize = 10.sp
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = null,
                        tint = PrimaryViolet,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Resolver no Modo Foco ➔",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = PrimaryViolet,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }
    }
}

/**
 * ========================================================
 * COMPONENTE: MODO FOCO (TELA CHEIA) COM PROGRESSÃO ADAPTATIVA
 * Inspirado na legibilidade do Brainly / Estuda.com
 * Oculta menus, exibe enunciado em fonte grande e alto contraste,
 * e alternativas limpas sem botões intermediários de 'Responder'.
 * ========================================================
 */
@Composable
fun FocusModeExerciseScreen(
    exercise: UniversalExercise,
    focusStreak: Int,
    onCloseFocusMode: () -> Unit,
    onSelectOption: (Int) -> Unit,
    onNextQuestion: (wasCorrect: Boolean) -> Unit
) {
    val scrollState = rememberScrollState()
    var sessionSelectedIndex by remember(exercise.id) { mutableStateOf<Int?>(null) }
    var showFullSolutionOverride by remember(exercise.id) { mutableStateOf(false) }

    val diffColor = when (exercise.difficulty) {
        "Fácil" -> SuccessEmerald
        "Mediano" -> GoldXp
        "Difícil" -> ErrorRose
        else -> TextSecondary
    }

    val hasAnswered = sessionSelectedIndex != null
    val isCorrect = hasAnswered && sessionSelectedIndex == exercise.correctIndex
    val shouldShowCommentedSolution = isCorrect || showFullSolutionOverride

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top Bar do Modo Foco (100% de foco na leitura)
        Surface(
            color = ObsidianSurface,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = BorderSubtle)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onCloseFocusMode,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Sair do Modo Foco",
                            tint = TextPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Modo Foco Adaptativo",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = diffColor.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = exercise.difficulty,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = diffColor,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                        Text(
                            text = "${exercise.category} • ${exercise.subtopic}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontSize = 10.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Indicador de Sequência de Acertos da Sessão
                if (focusStreak > 0) {
                    Surface(
                        color = GoldXp.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = KnowverseIcons.StreakFire,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "$focusStreak acerto${if (focusStreak > 1) "s" else ""}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GoldXp,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }
        }

        // Banner informativo da calibração adaptativa quando o aluno responde
        AnimatedVisibility(
            visible = hasAnswered,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Surface(
                color = if (isCorrect) SuccessEmerald.copy(alpha = 0.15f) else ErrorRose.copy(alpha = 0.15f),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        if (isCorrect) SuccessEmerald else ErrorRose
                    )
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = if (isCorrect) Icons.AutoMirrored.Filled.TrendingUp else Icons.AutoMirrored.Filled.TrendingDown,
                        contentDescription = null,
                        tint = if (isCorrect) SuccessEmerald else ErrorRose,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (isCorrect)
                            "Excelente! Resposta correta (+${exercise.xpReward} XP). Confira a resolução abaixo."
                        else
                            "Resposta incorreta. Veja a dica de lógica abaixo para repensar!",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (isCorrect) SuccessEmerald else ErrorRose,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp
                        )
                    )
                }
            }
        }

        // Corpo Principal de Leitura Imersiva (Rolagem Confortável com weight)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. TIPOGRAFIA DO ENUNCIADO: Fonte grande, negrito e alto contraste (Brainly)
            Surface(
                color = SurfaceElevated,
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ENUNCIADO DA QUESTÃO",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PrimaryViolet,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        Text(
                            text = exercise.source,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextTertiary,
                                fontSize = 10.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    MathFormattedText(
                        text = exercise.problemStatement,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            lineHeight = 26.sp
                        )
                    )
                }
            }

            // Recurso Visual GeoGebra Interativo se houver
            if (exercise.geoGebraConfig != null) {
                GeoGebraMathGraphView(
                    config = exercise.geoGebraConfig,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Recurso Visual de Código Python se houver
            if (exercise.codeSnippet != null) {
                Surface(
                    color = Color(0xFF0F172A),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Code,
                                contentDescription = null,
                                tint = PrimaryViolet,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Código Python para Análise:",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = PrimaryViolet,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = exercise.codeSnippet,
                            style = CodeTextStyle.copy(
                                color = Color(0xFF38BDF8),
                                fontSize = 12.sp,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }
            }

            // Tabela Visual se houver
            if (exercise.visualTable != null) {
                RenderVisualTable(table = exercise.visualTable)
            }

            // Imagem Visual se houver
            if (exercise.visualDrawableRes != null) {
                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = exercise.visualDrawableRes),
                        contentDescription = "Figura da questão",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            // 2. ALTERNATIVAS LIMPAS (A, B, C, D, E)
            Text(
                text = if (!hasAnswered) "Selecione a alternativa correta:" else "Alternativa assinalada:",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary,
                    fontSize = 11.sp,
                    letterSpacing = 0.5.sp
                )
            )

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                exercise.options.forEachIndexed { index, optionText ->
                    val optionLetter = ('A' + index).toString()
                    val isOptionSelected = sessionSelectedIndex == index
                    val isOptionCorrect = index == exercise.correctIndex

                    // Se errou, NÃO revelar a resposta correta em verde para incentivar a lógica
                    val showAsCorrect = (isCorrect && isOptionCorrect) || (showFullSolutionOverride && isOptionCorrect)
                    val showAsIncorrect = hasAnswered && isOptionSelected && !isCorrect

                    val backgroundColor = when {
                        showAsCorrect -> SuccessEmerald.copy(alpha = 0.18f)
                        showAsIncorrect -> ErrorRose.copy(alpha = 0.18f)
                        else -> ObsidianSurface
                    }

                    val borderColor = when {
                        showAsCorrect -> SuccessEmerald
                        showAsIncorrect -> ErrorRose
                        isOptionSelected -> PrimaryViolet
                        else -> BorderSubtle
                    }

                    Surface(
                        color = backgroundColor,
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !hasAnswered) {
                                sessionSelectedIndex = index
                                onSelectOption(index)
                            }
                            .testTag("focus_opt_${exercise.id}_$optionLetter")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = when {
                                    showAsCorrect -> SuccessEmerald
                                    showAsIncorrect -> ErrorRose
                                    isOptionSelected -> PrimaryViolet
                                    else -> SurfaceCard
                                },
                                shape = CircleShape,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = optionLetter,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = if (isOptionSelected || showAsCorrect) Color.White else TextSecondary,
                                            fontSize = 12.sp
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Box(modifier = Modifier.weight(1f)) {
                                MathFormattedText(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = TextPrimary,
                                        fontSize = 14.sp,
                                        fontWeight = if (isOptionSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                )
                            }

                            if (showAsCorrect) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Correto",
                                    tint = SuccessEmerald,
                                    modifier = Modifier.size(18.dp)
                                )
                            } else if (showAsIncorrect) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Incorreto",
                                    tint = ErrorRose,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 3. FEEDBACK PEDAGÓGICO: GABARITO COMENTADO (ACERTO) OU DICA DE LÓGICA (ERRO)
            if (hasAnswered) {
                if (shouldShowCommentedSolution) {
                    // ==========================================
                    // GABARITO COMENTADO (APÓS ACERTO OU PEDIDO)
                    // ==========================================
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("commented_solution_card")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = null,
                                    tint = SuccessEmerald,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "GABARITO COMENTADO & RESOLUÇÃO COMPLETA",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SuccessEmerald,
                                        fontSize = 12.5.sp,
                                        letterSpacing = 0.5.sp
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Resumo da Alternativa Correta
                            Surface(
                                color = SuccessEmerald.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Alternativa Correta: ${('A' + exercise.correctIndex)} — ${exercise.options[exercise.correctIndex]}",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SuccessEmerald,
                                        fontSize = 12.sp
                                    ),
                                    modifier = Modifier.padding(10.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Conceito / Método-Chave
                            if (exercise.keyMethod.isNotBlank()) {
                                Text(
                                    text = "Conceito & Método-Chave:",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = exercise.keyMethod,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // Passos da Solução
                            Text(
                                text = "Passo a Passo da Resolução:",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TextPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            exercise.solutionSteps.forEachIndexed { sIdx, step ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Surface(
                                        color = SurfaceElevated,
                                        shape = CircleShape,
                                        modifier = Modifier.size(20.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = "${sIdx + 1}",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    color = PrimaryViolet,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 10.sp
                                                )
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Box(modifier = Modifier.weight(1f)) {
                                        MathFormattedText(
                                            text = step,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 12.sp,
                                                lineHeight = 18.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // ==========================================
                    // DICA PEDAGÓGICA (CASO DE ERRO)
                    // ==========================================
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("pedagogical_hint_card")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "DICA PEDAGÓGICA (Pense na Lógica)",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = GoldXp,
                                        fontSize = 12.5.sp,
                                        letterSpacing = 0.5.sp
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Não se preocupe com o erro! Analise a pista abaixo para refletir sobre a linha de raciocínio antes de tentar novamente:",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.5.sp,
                                    lineHeight = 16.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Surface(
                                color = SurfaceElevated,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    MathFormattedText(
                                        text = exercise.pedagogicalAlert.ifBlank {
                                            "Analise com calma os dados fornecidos no enunciado. Identifique a grandeza pedida e revise as fórmulas ou relações proporcionais antes de marcar a resposta."
                                        },
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = TextPrimary,
                                            fontSize = 12.5.sp,
                                            lineHeight = 18.sp
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Botões de ação para tentar de novo ou revelar gabarito
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        sessionSelectedIndex = null
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = null,
                                        tint = TextPrimary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Tentar Novamente",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextPrimary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                                Button(
                                    onClick = {
                                        showFullSolutionOverride = true
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Visibility,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Ver Resolução",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextSecondary,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // 4. Barra Fixa Inferior: Botão "Próxima Questão"
        Surface(
            color = ObsidianSurface,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = BorderSubtle)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = when {
                        !hasAnswered -> "Selecione uma alternativa acima ou avance para buscar outra questão."
                        isCorrect -> "Excelente! O algoritmo calibrará uma questão igual ou superior."
                        else -> "Questão desafiadora! O algoritmo direcionará para um reforço mais fácil deste tema."
                    },
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = when {
                            !hasAnswered -> TextTertiary
                            isCorrect -> SuccessEmerald
                            else -> GoldXp
                        },
                        fontSize = 11.sp
                    )
                )

                Button(
                    onClick = {
                        onNextQuestion(isCorrect)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCorrect) SuccessEmerald else PrimaryViolet
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("focus_mode_next_button")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Próxima Questão",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Renderizador de tabela visual com cabeçalhos e linhas formatadas.
 */
@Composable
private fun RenderVisualTable(table: VisualTable) {
    Surface(
        color = SurfaceElevated,
        shape = RoundedCornerShape(6.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = table.title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = GoldXp,
                    fontSize = 11.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))

            // Cabeçalho da tabela
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ObsidianSurface, RoundedCornerShape(4.dp))
                    .padding(vertical = 4.dp, horizontal = 6.dp)
            ) {
                table.headers.forEach { header ->
                    Text(
                        text = header,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Linhas da tabela
            table.rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 6.dp)
                ) {
                    row.forEach { cell ->
                        Text(
                            text = cell,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

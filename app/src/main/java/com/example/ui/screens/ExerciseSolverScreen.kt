package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.exercise.ExerciseDiscipline
import com.example.data.exercise.UniversalExercise
import com.example.data.exercise.UniversalExerciseBank
import com.example.data.exercise.VisualTable
import com.example.data.local.UserProgressRepository
import com.example.ui.components.GeoGebraMathGraphView
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.MathFormattedText
import com.example.ui.components.TopUserStatsBar
import kotlinx.coroutines.launch
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

@Composable
fun ExerciseSolverScreen(
    progressRepository: UserProgressRepository,
    userPhotoUrl: String? = null,
    modifier: Modifier = Modifier
) {
    val userProgress by progressRepository.progressState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedDiscipline by remember { mutableStateOf(ExerciseDiscipline.ALL) }
    var selectedSubtopic by remember { mutableStateOf("all") }
    var selectedDifficulty by remember { mutableStateOf("all") }
    var onlyVisuals by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    // State of user answers: key = exercise ID, value = selected index
    val userAnswers = remember { mutableStateMapOf<String, Int>() }
    // State of expanded exercise cards: key = exercise ID, value = Boolean
    val expandedCardStates = remember { mutableStateMapOf<String, Boolean>() }
    // State of expanded solutions: key = exercise ID, value = Boolean
    val expandedSolutions = remember { mutableStateMapOf<String, Boolean>() }
    // State of completed items in this session
    val solvedExercises = remember { mutableStateMapOf<String, Boolean>() }

    // Subtópicos dinâmicos de acordo com a matéria/disciplina selecionada
    val subtopicOptions = remember(selectedDiscipline) {
        when (selectedDiscipline) {
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
                "Geometria Analítica & Gráficos" to "GeoGebra & Geometria",
                "if e else (Condicionais)" to "Python: if/else",
                "for & while (Laços)" to "Python: Loops",
                "Classes & POO" to "Python: POO",
                "Frações e Decimais" to "Mat. Básica: Frações",
                "MMC e MDC" to "Mat. Básica: MMC/MDC",
                "Regra de Três" to "Mat. Básica: Regra de Três",
                "Porcentagem" to "Mat. Básica: Porcentagem",
                "Limites & Continuidade" to "Cálculo: Limites",
                "Derivadas & Retas Tangentes" to "Cálculo: Derivadas",
                "Integrais & TFC" to "Cálculo: Integrais"
            )
        }
    }

    val difficulties = listOf(
        "all" to "Todas",
        "Fácil" to "Fácil",
        "Mediano" to "Médio",
        "Difícil" to "Difícil"
    )

    val filteredList = remember(
        searchQuery,
        selectedDiscipline,
        selectedSubtopic,
        selectedDifficulty,
        onlyVisuals
    ) {
        UniversalExerciseBank.filter(
            discipline = selectedDiscipline,
            subtopic = selectedSubtopic,
            difficulty = selectedDifficulty,
            query = searchQuery,
            onlyWithVisuals = onlyVisuals
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
    ) {
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = userPhotoUrl
        )

        // Header Title & Context
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = PrimaryViolet.copy(alpha = 0.2f),
                            shape = CircleShape,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = PrimaryViolet,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Banco de Resolução de Exercícios",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = "Gabarito Comentado • Programação, Matemática Básica & Cálculo",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 11.sp)
                            )
                        }
                    }

                    // Stat Badge
                    Surface(
                        color = GoldXp.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.4f))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${filteredList.size} itens",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GoldXp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Interactive Search Input
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = "Pesquisar por matéria, código, 'if e else', teorema, método ou termo...",
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
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("exercise_search_input")
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Hierarchical Breadcrumb: Programação -> Python -> if e else -> Nível
                Surface(
                    color = SurfaceCard,
                    shape = RoundedCornerShape(4.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().testTag("filter_breadcrumb_pathway")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Filtro:",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextTertiary,
                                fontSize = 10.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = buildString {
                                append(selectedDiscipline.label)
                                if (selectedDiscipline == ExerciseDiscipline.PROGRAMMING) {
                                    append(" › Python")
                                } else if (selectedDiscipline == ExerciseDiscipline.BASIC_MATH) {
                                    append(" › Mat. Básica")
                                } else if (selectedDiscipline == ExerciseDiscipline.CALCULUS) {
                                    append(" › Cálculo I")
                                }
                                if (selectedSubtopic != "all") {
                                    append(" › ")
                                    append(selectedSubtopic)
                                }
                                append(" › ")
                                append(
                                    when (selectedDifficulty) {
                                        "Fácil" -> "Fácil"
                                        "Mediano" -> "Médio"
                                        "Difícil" -> "Difícil"
                                        else -> "Todos os Níveis"
                                    }
                                )
                            },
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GoldXp,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 1. Categoria / Matéria (Nível 1 da hierarquia: Programação -> Python -> if/else)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(ExerciseDiscipline.values()) { disc ->
                        val isSelected = selectedDiscipline == disc
                        val icon = when (disc) {
                            ExerciseDiscipline.ALL -> Icons.Default.FilterList
                            ExerciseDiscipline.PROGRAMMING -> Icons.Default.Code
                            ExerciseDiscipline.BASIC_MATH -> Icons.Default.AutoAwesome
                            ExerciseDiscipline.CALCULUS -> KnowverseIcons.Math
                        }
                        val activeColor = when (disc) {
                            ExerciseDiscipline.ALL -> PrimaryViolet
                            ExerciseDiscipline.PROGRAMMING -> PrimaryViolet
                            ExerciseDiscipline.BASIC_MATH -> SuccessEmerald
                            ExerciseDiscipline.CALCULUS -> GoldXp
                        }

                        Surface(
                            color = if (isSelected) activeColor else SurfaceCard,
                            shape = RoundedCornerShape(4.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) activeColor else BorderSubtle
                            ),
                            modifier = Modifier
                                .clickable {
                                    selectedDiscipline = disc
                                    selectedSubtopic = "all" // reseta o subtópico para coincidir com a matéria
                                }
                                .padding(vertical = 1.dp)
                                .testTag("filter_disc_${disc.tag}")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = if (isSelected) Color.White else activeColor,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = disc.label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else TextSecondary
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 2. Subtópico Específico (Nível 2 da hierarquia)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(subtopicOptions) { (key, label) ->
                        val isSelected = selectedSubtopic == key
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedSubtopic = key },
                            label = {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PrimaryViolet,
                                selectedLabelColor = Color.White,
                                containerColor = SurfaceCard,
                                labelColor = TextSecondary
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = BorderSubtle,
                                selectedBorderColor = PrimaryViolet
                            ),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.height(26.dp).testTag("filter_subtopic_$key")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 3. Dificuldade & Filtro de Recursos Visuais (Nível 3)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nível:",
                            style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary, fontSize = 10.sp)
                        )
                        difficulties.forEach { (key, label) ->
                            val isSelected = selectedDifficulty == key
                            Surface(
                                color = if (isSelected) PrimaryViolet.copy(alpha = 0.25f) else SurfaceCard,
                                shape = RoundedCornerShape(4.dp),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isSelected) PrimaryViolet else BorderSubtle
                                ),
                                modifier = Modifier
                                    .clickable { selectedDifficulty = key }
                                    .padding(vertical = 1.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color.White else TextSecondary
                                    ),
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    // Toggle: Com Elementos Visuais (Tabelas, Código ou Gráficos)
                    Surface(
                        color = if (onlyVisuals) SuccessEmerald.copy(alpha = 0.18f) else SurfaceCard,
                        shape = RoundedCornerShape(4.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (onlyVisuals) SuccessEmerald else BorderSubtle
                        ),
                        modifier = Modifier
                            .clickable { onlyVisuals = !onlyVisuals }
                            .padding(vertical = 1.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = null,
                                tint = if (onlyVisuals) SuccessEmerald else TextTertiary,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Com Recursos Visuais",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    fontWeight = if (onlyVisuals) FontWeight.Bold else FontWeight.Normal,
                                    color = if (onlyVisuals) SuccessEmerald else TextSecondary
                                )
                            )
                        }
                    }
                }
            }
        }

        // Exercises List
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
                        text = "Nenhum exercício encontrado com esses filtros.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            searchQuery = ""
                            selectedDiscipline = ExerciseDiscipline.ALL
                            selectedSubtopic = "all"
                            selectedDifficulty = "all"
                            onlyVisuals = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Resetar Filtros", fontSize = 12.sp)
                    }
                }
            }
        } else {
            // Barra de contagem e controles de expandir/recolher tudo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${filteredList.size} exercícios encontrados",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    TextButton(
                        onClick = {
                            filteredList.forEach { ex -> expandedCardStates[ex.id] = true }
                        },
                        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Expandir todos", fontSize = 11.sp, color = PrimaryViolet)
                    }
                    TextButton(
                        onClick = {
                            filteredList.forEach { ex -> expandedCardStates[ex.id] = false }
                        },
                        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Recolher todos", fontSize = 11.sp, color = TextTertiary)
                    }
                }
            }

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(filteredList, key = { _, item -> item.id }) { index, item ->
                    val selectedIndex = userAnswers[item.id]
                    val isSolved = solvedExercises[item.id] == true
                    val isCardExpanded = expandedCardStates[item.id] ?: (index == 0)
                    val isSolutionExpanded = expandedSolutions[item.id] == true

                    UniversalExerciseCard(
                        item = item,
                        indexInList = index,
                        totalCount = filteredList.size,
                        selectedIndex = selectedIndex,
                        isSolved = isSolved,
                        isCardExpanded = isCardExpanded,
                        isSolutionExpanded = isSolutionExpanded,
                        onToggleCardExpand = {
                            expandedCardStates[item.id] = !isCardExpanded
                        },
                        onToggleSolutionExpand = {
                            expandedSolutions[item.id] = !isSolutionExpanded
                        },
                        onSelectOption = { optIndex ->
                            userAnswers[item.id] = optIndex
                            if (optIndex == item.correctIndex && !isSolved) {
                                solvedExercises[item.id] = true
                                progressRepository.recordCheckIn()
                                progressRepository.addBonusXp(item.xpReward)
                            }
                        },
                        onProceedToNext = {
                            // 1. Fecha o exercício atual resolvido
                            expandedCardStates[item.id] = false
                            // 2. Abre e rola para o próximo exercício
                            if (index + 1 < filteredList.size) {
                                val nextExercise = filteredList[index + 1]
                                expandedCardStates[nextExercise.id] = true
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index + 1)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun UniversalExerciseCard(
    item: UniversalExercise,
    indexInList: Int,
    totalCount: Int,
    selectedIndex: Int?,
    isSolved: Boolean,
    isCardExpanded: Boolean,
    isSolutionExpanded: Boolean,
    onToggleCardExpand: () -> Unit,
    onToggleSolutionExpand: () -> Unit,
    onSelectOption: (Int) -> Unit,
    onProceedToNext: (() -> Unit)?,
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
        ExerciseDiscipline.ALL -> PrimaryViolet
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = if (isSolved) 1.5.dp else 1.dp,
                color = if (isSolved) SuccessEmerald.copy(alpha = 0.8f) else BorderSubtle,
                shape = RoundedCornerShape(10.dp)
            )
            .testTag("exercise_card_${item.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Bar: Discipline + ID + Subtopic + Diff + XP + Status + Chevron
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleCardExpand() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    // Badge Número da Questão
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(4.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle)
                    ) {
                        Text(
                            text = "#${indexInList + 1}",
                            style = CodeTextStyle.copy(
                                color = GoldXp,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    // Discipline Pill
                    Surface(
                        color = disciplineBadgeColor.copy(alpha = 0.18f),
                        shape = RoundedCornerShape(4.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, disciplineBadgeColor.copy(alpha = 0.5f))
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

                    // Difficulty Badge
                    Surface(
                        color = diffColor.copy(alpha = 0.15f),
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

                    // Badge GeoGebra se houver gráfico
                    if (item.geoGebraConfig != null) {
                        Surface(
                            color = Color(0xFF1E3A8A).copy(alpha = 0.35f),
                            shape = RoundedCornerShape(4.dp),
                            border = androidx.compose.foundation.BorderStroke(0.6.dp, Color(0xFF60A5FA).copy(alpha = 0.6f))
                        ) {
                            Text(
                                text = "GeoGebra",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF93C5FD),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp
                                ),
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Status Badge
                    if (isSolved) {
                        Surface(
                            color = SuccessEmerald.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, SuccessEmerald.copy(alpha = 0.6f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = SuccessEmerald,
                                    modifier = Modifier.size(11.dp)
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
                        }
                    } else if (selectedIndex != null && selectedIndex != item.correctIndex) {
                        Surface(
                            color = ErrorRose.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(4.dp),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, ErrorRose.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = "Incorreto",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = ErrorRose,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                    } else {
                        // XP Reward Badge
                        Surface(
                            color = GoldXp.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "+${item.xpReward} XP",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GoldXp,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                    }

                    // Botão Chevron
                    IconButton(
                        onClick = onToggleCardExpand,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (isCardExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = if (isCardExpanded) "Recolher exercício" else "Expandir exercício",
                            tint = if (isCardExpanded) PrimaryViolet else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Subtopic Title & Source
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleCardExpand() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.subtopic,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextTertiary,
                        fontSize = 10.sp
                    )
                )
            }

            // Prévia quando recolhido
            if (!isCardExpanded) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.problemStatement,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp
                    ),
                    modifier = Modifier.clickable { onToggleCardExpand() }
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggleCardExpand() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = if (isSolved) "Toque para revisar gabarito ▾" else "Toque para expandir e resolver ▾",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (isSolved) SuccessEmerald else PrimaryViolet,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp
                        )
                    )
                }
            }

            // Conteúdo Expandido Completo
            AnimatedVisibility(
                visible = isCardExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    // Enunciado / Problem Statement with LaTeX Math
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.padding(12.dp)) {
                            MathFormattedText(
                                text = item.problemStatement,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextPrimary,
                                    lineHeight = 20.sp
                                )
                            )
                        }
                    }

                    // Recurso Visual GeoGebra Interativo
                    if (item.geoGebraConfig != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        GeoGebraMathGraphView(
                            config = item.geoGebraConfig,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

            // Visual Resource 1: Code Snippet
            if (item.codeSnippet != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = Color(0xFF0F172A),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Code,
                                contentDescription = null,
                                tint = PrimaryViolet,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Código Python para Análise:",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = PrimaryViolet,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = item.codeSnippet,
                            style = CodeTextStyle.copy(
                                color = Color(0xFF38BDF8),
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        )
                    }
                }
            }

            // Visual Resource 2: Terminal Output
            if (item.terminalOutput != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = Color(0xFF0A0F1D),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Terminal,
                                contentDescription = null,
                                tint = SuccessEmerald,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Saída do Terminal (Console):",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = SuccessEmerald,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.terminalOutput,
                            style = CodeTextStyle.copy(
                                color = Color(0xFFF1F5F9),
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }

            // Visual Resource 3: Visual Table (Decomposição, Grandezas, etc.)
            if (item.visualTable != null) {
                Spacer(modifier = Modifier.height(10.dp))
                RenderVisualTable(table = item.visualTable)
            }

            // Visual Resource 4: Diagram / Visual Chart Image
            if (item.visualDrawableRes != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = item.visualDrawableRes),
                        contentDescription = "Gráfico Diagnóstico",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Multiple-Choice Options (A-E)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                item.options.forEachIndexed { index, optionText ->
                    val optionLetter = ('A' + index).toString()
                    val isSelected = selectedIndex == index
                    val isCorrect = index == item.correctIndex
                    val hasAnswered = selectedIndex != null

                    val backgroundColor = when {
                        hasAnswered && isSelected && isCorrect -> SuccessEmerald.copy(alpha = 0.2f)
                        hasAnswered && isSelected && !isCorrect -> ErrorRose.copy(alpha = 0.2f)
                        hasAnswered && isCorrect -> SuccessEmerald.copy(alpha = 0.12f)
                        else -> SurfaceElevated
                    }

                    val borderColor = when {
                        hasAnswered && isSelected && isCorrect -> SuccessEmerald
                        hasAnswered && isSelected && !isCorrect -> ErrorRose
                        hasAnswered && isCorrect -> SuccessEmerald.copy(alpha = 0.6f)
                        isSelected -> PrimaryViolet
                        else -> BorderSubtle
                    }

                    Surface(
                        color = backgroundColor,
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectOption(index) }
                            .testTag("opt_${item.id}_$optionLetter")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = if (isSelected) PrimaryViolet else SurfaceCard,
                                shape = CircleShape,
                                modifier = Modifier.size(24.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = optionLetter,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) Color.White else TextSecondary,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Box(modifier = Modifier.weight(1f)) {
                                MathFormattedText(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                    )
                                )
                            }

                            if (hasAnswered && isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Correto",
                                    tint = SuccessEmerald,
                                    modifier = Modifier.size(16.dp)
                                )
                            } else if (hasAnswered && isSelected && !isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Incorreto",
                                    tint = ErrorRose,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Solved status or Incorrect banner
            if (selectedIndex != null) {
                Spacer(modifier = Modifier.height(10.dp))
                if (selectedIndex == item.correctIndex) {
                    Surface(
                        color = SuccessEmerald.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = SuccessEmerald,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Resposta Correta! +${item.xpReward} XP creditados no seu progresso.",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = SuccessEmerald,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.5.sp
                                )
                            )
                        }
                    }

                    // BOTÃO PROSSEGUIR PARA O PRÓXIMO EXERCÍCIO (MANDATÓRIO)
                    if (onProceedToNext != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = onProceedToNext,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SuccessEmerald,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("btn_proceed_next_${item.id}")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (indexInList + 1 < totalCount) {
                                        "Prosseguir para o Próximo Exercício (${indexInList + 2}/$totalCount)"
                                    } else {
                                        "🎉 Todos os Exercícios Concluídos!"
                                    },
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = if (indexInList + 1 < totalCount) Icons.AutoMirrored.Filled.ArrowForward else Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                } else {
                    Surface(
                        color = ErrorRose.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRose.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = ErrorRose,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Opção incorreta. Analise o passo a passo da lousa abaixo para dominar a questão!",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = ErrorRose,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Toggle Expand Button: "Ver Resolução Completa da Lousa"
            Surface(
                color = SurfaceElevated,
                shape = RoundedCornerShape(6.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleSolutionExpand() }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSolutionExpanded) "Ocultar Resolução Comentada" else "Ver Resolução Completa da Lousa",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = PrimaryViolet,
                                fontSize = 11.sp
                            )
                        )
                    }

                    Icon(
                        imageVector = if (isSolutionExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Expanded Analytical Blackboard Solution
            AnimatedVisibility(
                visible = isSolutionExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Blackboard container
                    Surface(
                        color = Color(0xFF0F172A),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    color = PrimaryViolet,
                                    shape = RoundedCornerShape(3.dp)
                                ) {
                                    Text(
                                        text = "GABARITO COMENTADO PASSO A PASSO",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = item.keyMethod,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontSize = 11.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            item.solutionSteps.forEach { step ->
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = "•",
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(end = 6.dp)
                                    )
                                    Box(modifier = Modifier.weight(1f)) {
                                        MathFormattedText(
                                            text = step,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = Color(0xFFE2E8F0),
                                                lineHeight = 18.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Pedagogical Alert & Conceptual Errors Box
                    if (item.pedagogicalAlert.isNotBlank()) {
                        Surface(
                            color = SurfaceCard,
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(16.dp).padding(top = 1.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Alerta Pedagógico de Prova & Erros Recorrentes:",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = GoldXp,
                                            fontSize = 11.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = item.pedagogicalAlert,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = TextSecondary,
                                            fontSize = 11.sp,
                                            lineHeight = 15.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
                }
            }
        }
    }
}

@Composable
fun RenderVisualTable(table: VisualTable) {
    Surface(
        color = Color(0xFF0F172A),
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

            // Headers
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryViolet.copy(alpha = 0.25f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                table.headers.forEach { header ->
                    Text(
                        text = header,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Rows
            table.rows.forEachIndexed { idx, rowData ->
                val rowBg = if (idx % 2 == 0) Color(0xFF1E293B) else Color(0xFF0F172A)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(rowBg, RoundedCornerShape(3.dp))
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    rowData.forEach { cell ->
                        Text(
                            text = cell,
                            style = CodeTextStyle.copy(
                                color = Color(0xFFE2E8F0),
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

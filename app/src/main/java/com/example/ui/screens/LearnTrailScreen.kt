package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.math.CalculusCurriculum
import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.LearningUnit
import com.example.data.model.Lesson
import com.example.data.model.UserProgress
import com.example.data.python.PythonCurriculum
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.TopUserStatsBar
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
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
fun LearnTrailScreen(
    userProgress: UserProgress,
    onSelectLesson: (Lesson) -> Unit,
    onStartFinalTest: (KahootModule) -> Unit,
    userPhotoUrl: String? = null,
    initialTier: DifficultyTier = DifficultyTier.BASIC,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("Todas") }

    // Course expansion states
    var isPythonExpanded by remember { mutableStateOf(true) }
    var isMathBasicExpanded by remember { mutableStateOf(true) }
    var isCalculusExpanded by remember { mutableStateOf(true) }

    // Active sub-module selection inside each course
    var selectedPythonTier by remember { mutableStateOf(initialTier) }
    var expandedPythonModule by remember { mutableStateOf<DifficultyTier?>(initialTier) }

    var isMathBasicLessonsOpen by remember { mutableStateOf(false) }

    var selectedCalculusModule by remember { mutableStateOf("Limites") }
    var expandedCalculusModule by remember { mutableStateOf<String?>(null) }

    val categories = listOf("Todas", "Programação", "Matemática")
    val showProgramming = selectedCategory == "Todas" || selectedCategory == "Programação"
    val showMath = selectedCategory == "Todas" || selectedCategory == "Matemática"

    val isIntermediateUnlocked = userProgress.isTierUnlocked(DifficultyTier.INTERMEDIATE)
    val isAdvancedUnlocked = userProgress.isTierUnlocked(DifficultyTier.ADVANCED)

    val context = androidx.compose.ui.platform.LocalContext.current
    val db = remember { com.example.data.local.room.KnowverseDatabase.getInstance(context) }
    val trailCacheRepo = remember { com.example.data.local.room.TrailCacheRepository(db.trailCacheDao(), context) }
    val cachedLessonCount by trailCacheRepo.cachedLessonCount.collectAsState(initial = 0)

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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))

                // Room Offline Cache Banner
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SuccessEmerald.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                        .testTag("room_offline_cache_badge")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(SuccessEmerald.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CloudDone,
                                contentDescription = "Cache Local Room",
                                tint = SuccessEmerald,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Cache Local Room Ativo",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = SuccessEmerald,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    color = SuccessEmerald.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "$cachedLessonCount lições offline",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = SuccessEmerald,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                    )
                                }
                            }
                            Text(
                                text = "Todas as lições e exercícios estão salvos no banco local para estudo sem internet.",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Cursos & Matérias",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )
                Text(
                    text = "Selecione uma matéria para acessar seus módulos e trilhas de aprendizagem.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Filter Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { cat ->
                        val isSelected = selectedCategory == cat
                        Surface(
                            color = if (isSelected) PrimaryViolet else ObsidianSurface,
                            shape = RoundedCornerShape(16.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) PrimaryViolet else BorderSubtle
                            ),
                            modifier = Modifier
                                .clickable { selectedCategory = cat }
                                .testTag("filter_cat_$cat")
                        ) {
                            Text(
                                text = cat,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else TextSecondary
                                ),
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
            }

            // ========================================================
            // CATEGORIA: PROGRAMAÇÃO -> CURSO PYTHON
            // ========================================================
            if (showProgramming) {
                item {
                    Text(
                        text = "Categoria: Programação",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = PrimaryViolet,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        ),
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                    )

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .clickable { isPythonExpanded = !isPythonExpanded }
                            .testTag("course_card_python")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(PrimaryViolet.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = KnowverseIcons.Python,
                                            contentDescription = null,
                                            tint = Color.Unspecified,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "Python",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = TextPrimary
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Surface(
                                                color = SuccessEmerald.copy(alpha = 0.15f),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = "3 Módulos",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = SuccessEmerald,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 10.sp
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }

                                        Text(
                                            text = "Introdução • Intermediário • Avançado",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 11.sp
                                            )
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = { isPythonExpanded = !isPythonExpanded },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isPythonExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expandir módulos",
                                        tint = TextSecondary
                                    )
                                }
                            }

                            // Expanded Python Modules
                            AnimatedVisibility(
                                visible = isPythonExpanded,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically()
                            ) {
                                Column(
                                    modifier = Modifier.padding(top = 14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))

                                    // Module 1: Introdução
                                    ExploreModuleCard(
                                        moduleNumber = 1,
                                        title = "Introdução",
                                        subtitle = "Primeiro nível • Início obrigatório",
                                        description = "Variáveis, tipos primitivos, operadores, condicionais if/else, laços while/for, listas e funções.",
                                        lessonsCount = "8 Aulas práticas",
                                        isUnlocked = true,
                                        isExpanded = expandedPythonModule == DifficultyTier.BASIC,
                                        accentColor = PrimaryViolet,
                                        onClick = {
                                            selectedPythonTier = DifficultyTier.BASIC
                                            expandedPythonModule = if (expandedPythonModule == DifficultyTier.BASIC) null else DifficultyTier.BASIC
                                        }
                                    )

                                    if (expandedPythonModule == DifficultyTier.BASIC) {
                                        ModuleLessonsInlineView(
                                            units = PythonCurriculum.getUnitsForTier(DifficultyTier.BASIC),
                                            finalTest = PythonCurriculum.basicKahoot,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }

                                    // Module 2: Intermediário
                                    ExploreModuleCard(
                                        moduleNumber = 2,
                                        title = "Intermediário",
                                        subtitle = if (isIntermediateUnlocked) "Segundo nível • Liberado" else "Segundo nível • Bloqueado",
                                        description = "Dicionários, tuplas, conjuntos, list comprehension, funções lambda, f-strings e tratamento de exceções.",
                                        lessonsCount = "8 Aulas práticas",
                                        isUnlocked = isIntermediateUnlocked,
                                        isExpanded = expandedPythonModule == DifficultyTier.INTERMEDIATE,
                                        lockReason = "Requer 100% no Teste Final da Introdução",
                                        accentColor = GoldXp,
                                        onClick = {
                                            if (isIntermediateUnlocked) {
                                                selectedPythonTier = DifficultyTier.INTERMEDIATE
                                                expandedPythonModule = if (expandedPythonModule == DifficultyTier.INTERMEDIATE) null else DifficultyTier.INTERMEDIATE
                                            }
                                        }
                                    )

                                    if (expandedPythonModule == DifficultyTier.INTERMEDIATE && isIntermediateUnlocked) {
                                        ModuleLessonsInlineView(
                                            units = PythonCurriculum.getUnitsForTier(DifficultyTier.INTERMEDIATE),
                                            finalTest = PythonCurriculum.intermediateKahoot,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }

                                    // Module 3: Avançado
                                    ExploreModuleCard(
                                        moduleNumber = 3,
                                        title = "Avançado",
                                        subtitle = if (isAdvancedUnlocked) "Terceiro nível • Liberado" else "Terceiro nível • Bloqueado",
                                        description = "Programação Orientada a Objetos, classes, dunder methods, herança, regex e estruturas de dados (Pilha).",
                                        lessonsCount = "7 Aulas práticas",
                                        isUnlocked = isAdvancedUnlocked,
                                        isExpanded = expandedPythonModule == DifficultyTier.ADVANCED,
                                        lockReason = "Requer 100% no Teste Final do Intermediário",
                                        accentColor = Color(0xFF38BDF8),
                                        onClick = {
                                            if (isAdvancedUnlocked) {
                                                selectedPythonTier = DifficultyTier.ADVANCED
                                                expandedPythonModule = if (expandedPythonModule == DifficultyTier.ADVANCED) null else DifficultyTier.ADVANCED
                                            }
                                        }
                                    )

                                    if (expandedPythonModule == DifficultyTier.ADVANCED && isAdvancedUnlocked) {
                                        ModuleLessonsInlineView(
                                            units = PythonCurriculum.getUnitsForTier(DifficultyTier.ADVANCED),
                                            finalTest = PythonCurriculum.advancedKahoot,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // ========================================================
            // CATEGORIA: MATEMÁTICA -> MATEMÁTICA BÁSICA & CÁLCULO I
            // ========================================================
            if (showMath) {
                // CURSO 1 DE MATEMÁTICA: MATEMÁTICA BÁSICA (ENEM)
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Categoria: Matemática",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldXp,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        ),
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                    )

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .clickable { isMathBasicExpanded = !isMathBasicExpanded }
                            .testTag("course_card_math_basic")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(SuccessEmerald.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Calculate,
                                            contentDescription = null,
                                            tint = SuccessEmerald,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "Matemática Básica",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = TextPrimary
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Surface(
                                                color = SuccessEmerald.copy(alpha = 0.15f),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = "12 Aulas",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = SuccessEmerald,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 10.sp
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }

                                        Text(
                                            text = "Ciclo ENEM • Nivelamento Universitário & Pré-Cálculo",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 11.sp
                                            )
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = { isMathBasicExpanded = !isMathBasicExpanded },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isMathBasicExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expandir Matemática Básica",
                                        tint = TextSecondary
                                    )
                                }
                            }

                            AnimatedVisibility(
                                visible = isMathBasicExpanded,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically()
                            ) {
                                Column(
                                    modifier = Modifier.padding(top = 14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))

                                    ExploreModuleCard(
                                        moduleNumber = 1,
                                        title = "Fundamentos da Matemática Básica",
                                        subtitle = "Frações, MMC, Potências, Regra de Três, Porcentagem e Equações",
                                        description = "Os 12 pilares essenciais: Frações, Decimais, MMC/MDC, Potenciação, Notação Científica, Unidades de Medida, Razão e Proporção, Regra de Três Composta, Porcentagem e Juros, Equações do 1º e 2º Grau, Geometria e Escalas.",
                                        lessonsCount = "12 Aulas práticas completas",
                                        isUnlocked = true,
                                        isExpanded = isMathBasicLessonsOpen,
                                        accentColor = SuccessEmerald,
                                        onClick = { isMathBasicLessonsOpen = !isMathBasicLessonsOpen }
                                    )

                                    if (isMathBasicLessonsOpen) {
                                        ModuleLessonsInlineView(
                                            units = CalculusCurriculum.getUnitsForModule("Matemática Básica"),
                                            finalTest = CalculusCurriculum.basicMathFinalTest,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // CURSO 2 DE MATEMÁTICA: CÁLCULO I
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .clickable { isCalculusExpanded = !isCalculusExpanded }
                            .testTag("course_card_calculus")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(GoldXp.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Functions,
                                            contentDescription = null,
                                            tint = GoldXp,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "Cálculo I",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = TextPrimary
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Surface(
                                                color = GoldXp.copy(alpha = 0.15f),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = "3 Módulos Visuais",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = GoldXp,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 10.sp
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }

                                        Text(
                                            text = "Limites, Derivadas e Integrais com Gráficos Diagnósticos",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 11.sp
                                            )
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = { isCalculusExpanded = !isCalculusExpanded },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isCalculusExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expandir Cálculo I",
                                        tint = TextSecondary
                                    )
                                }
                            }

                            AnimatedVisibility(
                                visible = isCalculusExpanded,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically()
                            ) {
                                Column(
                                    modifier = Modifier.padding(top = 14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))

                                    // Module 1: Limites
                                    ExploreModuleCard(
                                        moduleNumber = 1,
                                        title = "Limites e Continuidade",
                                        subtitle = "Aproximação, Limites Laterais, Fatoração e Infinitos",
                                        description = "Conceito intuitivo de limites, limites laterais, técnicas de fatoração 0/0, limites infinitos e continuidade.",
                                        lessonsCount = "10 Aulas completas",
                                        isUnlocked = true,
                                        isExpanded = expandedCalculusModule == "Limites",
                                        accentColor = GoldXp,
                                        onClick = {
                                            selectedCalculusModule = "Limites"
                                            expandedCalculusModule = if (expandedCalculusModule == "Limites") null else "Limites"
                                        }
                                    )

                                    if (expandedCalculusModule == "Limites") {
                                        ModuleLessonsInlineView(
                                            units = CalculusCurriculum.getUnitsForModule("Limites"),
                                            finalTest = CalculusCurriculum.calculusLimitsFinalTest,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }

                                    // Module 2: Derivadas
                                    ExploreModuleCard(
                                        moduleNumber = 2,
                                        title = "Derivadas e Aplicações",
                                        subtitle = "Regras do Tombo, Cadeia, Otimização e L'Hôpital",
                                        description = "Taxa de variação, regras operatórias, regra da cadeia, concavidade, problemas de máximos/mínimos e regra de L'Hôpital.",
                                        lessonsCount = "8 Aulas com gráficos",
                                        isUnlocked = true,
                                        isExpanded = expandedCalculusModule == "Derivadas",
                                        accentColor = GoldXp,
                                        onClick = {
                                            selectedCalculusModule = "Derivadas"
                                            expandedCalculusModule = if (expandedCalculusModule == "Derivadas") null else "Derivadas"
                                        }
                                    )

                                    if (expandedCalculusModule == "Derivadas") {
                                        ModuleLessonsInlineView(
                                            units = CalculusCurriculum.getUnitsForModule("Derivadas"),
                                            finalTest = CalculusCurriculum.calculusDerivativesFinalTest,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }

                                    // Module 3: Integrais
                                    ExploreModuleCard(
                                        moduleNumber = 3,
                                        title = "Integrais e Teorema Fundamental",
                                        subtitle = "Antiderivadas, Áreas, Substituição e Partes",
                                        description = "Somas de Riemann, integrais imediatas, teorema fundamental do cálculo, método da substituição (u-du) e integração por partes.",
                                        lessonsCount = "10 Aulas com lousa diagnóstica",
                                        isUnlocked = true,
                                        isExpanded = expandedCalculusModule == "Integrais",
                                        accentColor = PrimaryViolet,
                                        onClick = {
                                            selectedCalculusModule = "Integrais"
                                            expandedCalculusModule = if (expandedCalculusModule == "Integrais") null else "Integrais"
                                        }
                                    )

                                    if (expandedCalculusModule == "Integrais") {
                                        ModuleLessonsInlineView(
                                            units = CalculusCurriculum.getUnitsForModule("Integrais"),
                                            finalTest = CalculusCurriculum.calculusIntegralsFinalTest,
                                            isTierUnlocked = true,
                                            userProgress = userProgress,
                                            onSelectLesson = onSelectLesson,
                                            onStartFinalTest = onStartFinalTest
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ExploreModuleCard(
    moduleNumber: Int,
    title: String,
    subtitle: String,
    description: String,
    lessonsCount: String,
    isUnlocked: Boolean,
    isExpanded: Boolean = false,
    lockReason: String? = null,
    accentColor: Color,
    onClick: () -> Unit
) {
    Surface(
        color = if (isUnlocked) SurfaceCard else ObsidianSurface.copy(alpha = 0.5f),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isExpanded) accentColor else if (isUnlocked) BorderSubtle else BorderSubtle.copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                if (isUnlocked) accentColor.copy(alpha = 0.2f)
                                else SurfaceElevated
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isUnlocked) {
                            Text(
                                text = "$moduleNumber",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = accentColor
                                )
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Bloqueado",
                                tint = TextTertiary,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "Módulo $moduleNumber: $title",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = if (isUnlocked) TextPrimary else TextTertiary
                            )
                        )
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = if (isUnlocked) TextSecondary else TextTertiary,
                                fontSize = 11.sp
                            )
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = lessonsCount,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Ver aulas",
                        tint = if (isUnlocked) TextSecondary else TextTertiary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (isUnlocked) TextSecondary else TextTertiary,
                    lineHeight = 16.sp,
                    fontSize = 11.sp
                )
            )

            if (!isUnlocked && lockReason != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = GoldXp,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = lockReason,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldXp,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ModuleLessonsInlineView(
    units: List<LearningUnit>,
    finalTest: KahootModule,
    isTierUnlocked: Boolean,
    userProgress: UserProgress,
    onSelectLesson: (Lesson) -> Unit,
    onStartFinalTest: (KahootModule) -> Unit
) {
    val allLessonsInTrack = remember(units) { units.flatMap { it.lessons } }
    val completedCountInTrack = allLessonsInTrack.count { userProgress.completedLessonIds.contains(it.id) }
    val areAllLessonsCompleted = allLessonsInTrack.isNotEmpty() && completedCountInTrack == allLessonsInTrack.size

    val savedTestScore = userProgress.completedKahootScores[finalTest.id] ?: 0
    val isTestPassedWith100 = userProgress.perfectTestModules.contains(finalTest.id)
    val isTestUnlocked = isTierUnlocked && areAllLessonsCompleted

    Surface(
        color = DeepNavy.copy(alpha = 0.5f),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Aulas Sequenciais do Módulo ($completedCountInTrack/${allLessonsInTrack.size})",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldXp
                    )
                )

                if (areAllLessonsCompleted) {
                    Surface(
                        color = SuccessEmerald.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "Aulas Concluídas",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SuccessEmerald,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            units.forEach { unit ->
                if (units.size > 1) {
                    Text(
                        text = "Subtópico ${unit.unitNumber}: ${unit.title}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryViolet
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                unit.lessons.forEach { lesson ->
                    val isCompleted = userProgress.completedLessonIds.contains(lesson.id)
                    val isActive = userProgress.currentLessonId == lesson.id

                    val lessonIndex = allLessonsInTrack.indexOfFirst { it.id == lesson.id }
                    val isPrecedingLessonDone = if (lessonIndex > 0) {
                        userProgress.completedLessonIds.contains(allLessonsInTrack[lessonIndex - 1].id)
                    } else true

                    val isLessonUnlocked = isTierUnlocked && (isCompleted || lessonIndex == 0 || isPrecedingLessonDone)
                    val isLocked = !isLessonUnlocked

                    TrailLessonNode(
                        lesson = lesson,
                        isCompleted = isCompleted,
                        isActive = isActive,
                        isLocked = isLocked,
                        onClick = { if (!isLocked) onSelectLesson(lesson) }
                    )
                }
            }

            // TESTE FINAL CARD DO MÓDULO
            val borderCardColor = when {
                isTestPassedWith100 -> SuccessEmerald.copy(alpha = 0.6f)
                isTestUnlocked -> GoldXp.copy(alpha = 0.6f)
                else -> BorderSubtle
            }

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .border(1.dp, borderCardColor, RoundedCornerShape(8.dp))
                    .clickable(enabled = isTestUnlocked) { onStartFinalTest(finalTest) }
                    .testTag("tier_final_test_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = finalTest.title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isTestUnlocked) TextPrimary else TextTertiary
                                )
                            )
                            if (savedTestScore > 0) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = if (isTestPassedWith100) SuccessEmerald.copy(alpha = 0.2f) else GoldXp.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = if (isTestPassedWith100) "100% Acertos" else "$savedTestScore pts",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (isTestPassedWith100) SuccessEmerald else GoldXp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = when {
                                !isTierUnlocked ->
                                    "Bloqueado. Complete o nível anterior com 100% no teste para liberar."
                                !areAllLessonsCompleted ->
                                    "Bloqueado: Conclua todas as aulas do módulo ($completedCountInTrack/${allLessonsInTrack.size}) para liberar o Teste Final."
                                isTestPassedWith100 ->
                                    "Concluído com 100% de Acerto! Emblema conquistado e nível superior liberado."
                                else ->
                                    "Disponível! Responda o desafio do módulo e acerte 100% para conquistar o Emblema e liberar o próximo módulo."
                            },
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = if (isTestUnlocked) TextSecondary else TextTertiary,
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                when {
                                    isTestPassedWith100 -> SuccessEmerald
                                    isTestUnlocked -> PrimaryViolet
                                    else -> SurfaceElevated
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isTestUnlocked) Icons.Default.WorkspacePremium else Icons.Default.Lock,
                            contentDescription = if (isTestUnlocked) "Iniciar Teste Final" else "Bloqueado",
                            tint = if (isTestUnlocked) Color.White else TextTertiary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrailLessonNode(
    lesson: Lesson,
    isCompleted: Boolean,
    isActive: Boolean,
    isLocked: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                isActive -> SurfaceElevated
                isCompleted -> ObsidianSurface
                else -> ObsidianSurface.copy(alpha = 0.7f)
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = when {
                    isActive -> PrimaryViolet
                    isCompleted -> SuccessEmerald.copy(alpha = 0.4f)
                    else -> BorderSubtle
                },
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(enabled = !isLocked, onClick = onClick)
            .testTag("trail_lesson_${lesson.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isCompleted -> SuccessEmerald.copy(alpha = 0.2f)
                                isActive -> PrimaryViolet.copy(alpha = 0.2f)
                                isLocked -> SurfaceElevated
                                else -> SurfaceElevated
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = when {
                            isCompleted -> Icons.Default.Check
                            isLocked -> Icons.Default.Lock
                            else -> Icons.Default.PlayArrow
                        },
                        contentDescription = null,
                        tint = when {
                            isCompleted -> SuccessEmerald
                            isActive -> PrimaryViolet
                            else -> TextTertiary
                        },
                        modifier = Modifier.size(14.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = if (isActive || isCompleted) FontWeight.SemiBold else FontWeight.Normal,
                            color = if (isLocked) TextTertiary else TextPrimary
                        )
                    )
                    Text(
                        text = lesson.conceptTitle,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TextTertiary,
                            fontSize = 11.sp
                        )
                    )
                }
            }

            Text(
                text = "+${lesson.xpReward} XP",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isCompleted) TextTertiary else GoldXp
                )
            )
        }
    }
}

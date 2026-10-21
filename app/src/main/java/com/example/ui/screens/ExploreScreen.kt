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
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.model.Lesson
import com.example.data.model.UserProgress
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
fun ExploreScreen(
    userProgress: UserProgress,
    onSelectTier: (DifficultyTier) -> Unit,
    onSelectLesson: (Lesson) -> Unit,
    userPhotoUrl: String? = null,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("Todas") }
    var isPythonExpanded by remember { mutableStateOf(true) }
    var isCalculusExpanded by remember { mutableStateOf(false) }

    val categories = listOf("Todas", "Programação", "Matemática")

    val showProgramming = selectedCategory == "Todas" || selectedCategory == "Programação"
    val showMath = selectedCategory == "Todas" || selectedCategory == "Matemática"

    val isIntermediateUnlocked = userProgress.isTierUnlocked(DifficultyTier.INTERMEDIATE)
    val isAdvancedUnlocked = userProgress.isTierUnlocked(DifficultyTier.ADVANCED)

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

                            // Expanded 3 Modules List
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

                                    // Module 1: Introdução (Starting level)
                                    ModuleCard(
                                        moduleNumber = 1,
                                        title = "Introdução",
                                        subtitle = "Primeiro nível • Início obrigatório",
                                        description = "Variáveis, tipos primitivos, operadores, condicionais if/else, laços while/for, listas e funções.",
                                        lessonsCount = "8 Aulas práticas",
                                        isUnlocked = true,
                                        accentColor = PrimaryViolet,
                                        onClick = { onSelectTier(DifficultyTier.BASIC) }
                                    )

                                    // Module 2: Intermediário
                                    ModuleCard(
                                        moduleNumber = 2,
                                        title = "Intermediário",
                                        subtitle = if (isIntermediateUnlocked) "Segundo nível • Liberado" else "Segundo nível • Bloqueado",
                                        description = "Dicionários, tuplas, conjuntos, list comprehension, funções lambda, f-strings e tratamento de exceções.",
                                        lessonsCount = "8 Aulas práticas",
                                        isUnlocked = isIntermediateUnlocked,
                                        lockReason = "Requer 100% no Teste Final da Introdução",
                                        accentColor = GoldXp,
                                        onClick = { if (isIntermediateUnlocked) onSelectTier(DifficultyTier.INTERMEDIATE) }
                                    )

                                    // Module 3: Avançado
                                    ModuleCard(
                                        moduleNumber = 3,
                                        title = "Avançado",
                                        subtitle = if (isAdvancedUnlocked) "Terceiro nível • Liberado" else "Terceiro nível • Bloqueado",
                                        description = "Programação Orientada a Objetos, classes, dunder methods, herança, regex e estruturas de dados (Pilha).",
                                        lessonsCount = "7 Aulas práticas",
                                        isUnlocked = isAdvancedUnlocked,
                                        lockReason = "Requer 100% no Teste Final do Intermediário",
                                        accentColor = Color(0xFF38BDF8),
                                        onClick = { if (isAdvancedUnlocked) onSelectTier(DifficultyTier.ADVANCED) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // ========================================================
            // CATEGORIA: MATEMÁTICA -> CURSO CÁLCULO I
            // ========================================================
            if (showMath) {
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
                                            imageVector = Icons.Default.Calculate,
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
                                            text = "Limites e Continuidade com Gráficos Diagnósticos",
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
                                        contentDescription = "Expandir módulos",
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

                                    ModuleCard(
                                        moduleNumber = 1,
                                        title = "Módulo 1: Matemática Básica (Ciclo ENEM)",
                                        subtitle = "Frações, MMC/MDC, Potências, Unidades, Proporções e Escalas",
                                        description = "Os 9 pilares essenciais: Frações, Decimais, MMC, MDC, Potenciação, Notação Científica, Conversão de Unidades, Razão, Regra de Três, Escalas e Porcentagem.",
                                        lessonsCount = "9 Aulas completas",
                                        isUnlocked = true,
                                        accentColor = SuccessEmerald,
                                        onClick = { onSelectLesson(CalculusCurriculum.basicMathUnit.lessons.first()) }
                                    )

                                    ModuleCard(
                                        moduleNumber = 2,
                                        title = "Módulo 2: Limites e Continuidade",
                                        subtitle = "Aproximação, Laterais e Indeterminações",
                                        description = "Conceito intuitivo de limites, limites laterais, fatoração 0/0, continuidade e assíntotas.",
                                        lessonsCount = "10 Aulas completas",
                                        isUnlocked = true,
                                        accentColor = GoldXp,
                                        onClick = { onSelectLesson(CalculusCurriculum.limitsUnit.lessons.first()) }
                                    )

                                    ModuleCard(
                                        moduleNumber = 3,
                                        title = "Módulo 3: Derivadas e Aplicações",
                                        subtitle = "P1 e P2: Taxas, Regras e Otimização",
                                        description = "Regras do tombo, produto, quociente e cadeia, aproximação linear, concavidade, L'Hôpital e otimização.",
                                        lessonsCount = "8 Aulas com gráficos",
                                        isUnlocked = true,
                                        accentColor = GoldXp,
                                        onClick = { onSelectLesson(CalculusCurriculum.derivativesUnit.lessons.first()) }
                                    )

                                    ModuleCard(
                                        moduleNumber = 4,
                                        title = "Módulo 4: Integrais",
                                        subtitle = "Áreas e Teorema Fundamental",
                                        description = "Somas de Riemann, integrais definidas, substituição e aplicações geométricas.",
                                        lessonsCount = "Aulas com lousa",
                                        isUnlocked = true,
                                        accentColor = PrimaryViolet,
                                        onClick = { onSelectLesson(CalculusCurriculum.integralsUnit.lessons.first()) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun ModuleCard(
    moduleNumber: Int,
    title: String,
    subtitle: String,
    description: String,
    lessonsCount: String,
    isUnlocked: Boolean,
    lockReason: String? = null,
    accentColor: Color,
    onClick: () -> Unit
) {
    Surface(
        color = if (isUnlocked) SurfaceCard else ObsidianSurface.copy(alpha = 0.5f),
        shape = RoundedCornerShape(6.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isUnlocked) accentColor.copy(alpha = 0.4f) else BorderSubtle.copy(alpha = 0.3f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isUnlocked, onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isUnlocked) accentColor.copy(alpha = 0.2f) else SurfaceElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$moduleNumber",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isUnlocked) accentColor else TextTertiary
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = if (isUnlocked) TextPrimary else TextTertiary
                            )
                        )
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isUnlocked) accentColor else TextTertiary,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                if (isUnlocked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Acessar",
                        tint = accentColor,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Bloqueado",
                        tint = TextTertiary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (isUnlocked) TextSecondary else TextTertiary,
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = lessonsCount,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = if (isUnlocked) TextSecondary else TextTertiary,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp
                    )
                )

                if (!isUnlocked && lockReason != null) {
                    Text(
                        text = lockReason,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFFF87171),
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}

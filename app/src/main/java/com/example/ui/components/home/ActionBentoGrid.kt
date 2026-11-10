package com.example.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.data.lore.KnowverseCharacter
import com.example.data.lore.KnowverseLoreRepository
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Lesson
import com.example.ui.components.KnowverseIcons
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.StreakFire
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

/**
 * Painel Central de Atividades e Trilhas do Knowverse.
 *
 * Estrutura de Hierarquia Visual Proporcional:
 * 1. Card Hero: "Continuar Aprendizado" (Destaque Principal com CTA amplo >= 48dp)
 * 2. Widget de Meta Diária & Consistência de Estudos
 * 3. Catálogo de Trilhas ENEM & Tecnologia (Grid 2x3 com Matemática, Física, Biologia, Química, Redação, Python)
 * 4. Destaque Especial: Laboratório de Redação ENEM (Matriz INEP C1–C5)
 * 5. Bloco Duplo de Ação: Banco de Exercícios Adaptativo x Simulados ENEM & Ranking
 */
@Composable
fun ActionBentoGrid(
    nextLesson: Lesson,
    userRankPosition: Int,
    totalXp: Int,
    onContinueLearningClick: (Lesson) -> Unit,
    onNavigateToTrail: () -> Unit,
    onNavigateToExercises: () -> Unit,
    onNavigateToEssayLab: () -> Unit,
    onNavigateToRanking: () -> Unit,
    modifier: Modifier = Modifier,
    activeSubjectName: String = "Python",
    subjectProgressMap: Map<String, Pair<Int, Int>> = emptyMap(),
    dailyXp: Int = 0,
    dailyGoalXp: Int = 50,
    reviewLessonCount: Int = 0,
    hasStudiedToday: Boolean = false,
    currentStreak: Int = 0,
    onNavigateToSimulados: () -> Unit = {}
) {
    val activeSubjectStats = subjectProgressMap[activeSubjectName] ?: (0 to 1)
    val activeSubjectPct = if (activeSubjectStats.second > 0) {
        ((activeSubjectStats.first.toFloat() / activeSubjectStats.second.toFloat()) * 100).toInt().coerceIn(0, 100)
    } else 0
    var selectedMentorForDetail by remember { mutableStateOf<KnowverseCharacter?>(null) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // ========================================================
        // 1. CARD HERO PRINCIPAL: CONTINUAR APRENDIZADO
        // ========================================================
        Surface(
            color = ObsidianSurface,
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.5f)),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onContinueLearningClick(nextLesson) }
                .testTag("bento_continue_learning")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Topo do Card: Badge de Status + XP Dourado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(PrimaryViolet.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = PrimaryViolet,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "CONTINUAR APRENDIZADO • ${activeSubjectName.uppercase()}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = PrimaryViolet,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    letterSpacing = 0.7.sp
                                )
                            )
                            Text(
                                text = "${activeSubjectStats.first}/${activeSubjectStats.second} aulas concluídas ($activeSubjectPct%)",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }

                    Surface(
                        color = GoldXp.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, GoldXp.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = KnowverseIcons.XpGem,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "+${nextLesson.xpReward} XP",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GoldXp,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.5.sp
                                )
                            )
                        }
                    }
                }

                // Corpo do Card: Título e Descrição da Aula
                Column {
                    Text(
                        text = nextLesson.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontSize = 16.sp,
                            lineHeight = 21.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Avanço guiado com teoria explicativa, código e exercícios práticos de fixação.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Botão de Ação Primária Amplo (M3 - 48dp de altura para acessibilidade ao toque)
                Button(
                    onClick = { onContinueLearningClick(nextLesson) },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Continuar Lição Agora",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.5.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // ========================================================
        // 2. META DIÁRIA & SEQUÊNCIA DE ESTUDOS
        // ========================================================
        val safeGoal = dailyGoalXp.coerceAtLeast(10)
        val dailyFraction = (dailyXp.toFloat() / safeGoal.toFloat()).coerceIn(0f, 1f)
        Surface(
            color = ObsidianSurface,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        val isFireActive = hasStudiedToday && currentStreak > 0
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(if (isFireActive) StreakFire.copy(alpha = 0.15f) else SurfaceElevated),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isFireActive) KnowverseIcons.StreakFire else KnowverseIcons.FlameInactive,
                                contentDescription = "Sequência",
                                tint = if (isFireActive) StreakFire else TextTertiary,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Column {
                            Text(
                                text = if (hasStudiedToday) "Meta de Hoje Concluída!" else "Meta Diária: $dailyXp / $safeGoal XP",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (hasStudiedToday) SuccessEmerald else TextPrimary,
                                    fontSize = 13.5.sp
                                )
                            )
                            Text(
                                text = if (reviewLessonCount > 0) {
                                    "Ofensiva: $currentStreak d • $reviewLessonCount ${if (reviewLessonCount == 1) "aula sugerida" else "aulas sugeridas"} para revisão"
                                } else if (hasStudiedToday) {
                                    "Sequência mantida: $currentStreak dias ativos seguidos."
                                } else {
                                    "Complete uma aula ou bateria de questões para manter sua sequência."
                                },
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.5.sp
                                )
                            )
                        }
                    }

                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.clickable { onNavigateToExercises() }
                    ) {
                        Text(
                            text = if (reviewLessonCount > 0) "Revisar ($reviewLessonCount)" else "Treinar",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PrimaryViolet,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }

                // Barra de progresso da meta diária de XP
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(SurfaceElevated)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(dailyFraction.coerceAtLeast(if (dailyXp > 0) 0.05f else 0f))
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(3.dp))
                            .background(if (hasStudiedToday || dailyFraction >= 1f) SuccessEmerald else PrimaryViolet)
                    )
                }
            }
        }

        // ========================================================
        // 3. TRILHAS DO KNOWVERSE: GRID 4x2 COM PROGRESSO REAL
        // ========================================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("bento_trail_banner"),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DISCIPLINAS & TRILHAS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary,
                        letterSpacing = 0.8.sp,
                        fontSize = 11.sp
                    )
                )

                Text(
                    text = "Ver Todas as Trilhas",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = PrimaryViolet,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.clickable { onNavigateToTrail() }
                )
            }

            // Grid de 8 áreas em 4 linhas x 2 colunas com indicadores reais de progresso
            val subjects = listOf(
                SubjectItem("Matemática", "ENEM, Básica & Cálculo", Color(0xFF3B82F6), KnowverseIcons.Math, subjectProgressMap["Matemática"], KnowverseLoreRepository.THEO) { onNavigateToTrail() },
                SubjectItem("Física", "Mecânica, Termo & Óptica", Color(0xFF06B6D4), KnowverseIcons.PhysicsAtom, subjectProgressMap["Física"], KnowverseLoreRepository.NESTOR) { onNavigateToTrail() },
                SubjectItem("Biologia", "Ecologia, Citologia & DNA", Color(0xFF10B981), KnowverseIcons.BiologyLeaf, subjectProgressMap["Biologia"], KnowverseLoreRepository.NESTOR) { onNavigateToTrail() },
                SubjectItem("Química", "Geral, Físico & Orgânica", Color(0xFFF59E0B), KnowverseIcons.ChemistryFlask, subjectProgressMap["Química"], KnowverseLoreRepository.NESTOR) { onNavigateToTrail() },
                SubjectItem("Humanas", "História & Geografia ENEM", Color(0xFFFBBF24), KnowverseIcons.GeographyGlobe, subjectProgressMap["Humanas"], KnowverseLoreRepository.DANTE) { onNavigateToTrail() },
                SubjectItem("Linguagens", "Interpretação & Literatura", Color(0xFF818CF8), KnowverseIcons.LanguagesBook, subjectProgressMap["Linguagens"], KnowverseLoreRepository.CLARA) { onNavigateToTrail() },
                SubjectItem("Redação", "Matriz INEP C1–C5", Color(0xFFEC4899), Icons.Default.Edit, null, KnowverseLoreRepository.CLARA) { onNavigateToEssayLab() },
                SubjectItem("Python", "Algoritmos & Terminal", Color(0xFF8B5CF6), KnowverseIcons.Python, subjectProgressMap["Python"], KnowverseLoreRepository.PIPA) { onNavigateToTrail() }
            )

            for (rowIndex in 0 until 4) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    for (colIndex in 0 until 2) {
                        val item = subjects[rowIndex * 2 + colIndex]
                        val prog = item.progress
                        val progFraction = if (prog != null && prog.second > 0) {
                            (prog.first.toFloat() / prog.second.toFloat()).coerceIn(0f, 1f)
                        } else 0f

                        Surface(
                            color = ObsidianSurface,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier
                                .weight(1f)
                                .height(82.dp)
                                .clickable { item.onClick() }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 10.dp, vertical = 8.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(9.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(item.accentColor.copy(alpha = 0.14f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.title,
                                            tint = item.accentColor,
                                            modifier = Modifier.size(17.dp)
                                        )
                                    }

                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = item.title,
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = TextPrimary,
                                                    fontSize = 12.5.sp
                                                ),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            if (prog != null && prog.second > 0) {
                                                Text(
                                                    text = "${prog.first}/${prog.second}",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = if (prog.first > 0) item.accentColor else TextTertiary,
                                                        fontSize = 10.5.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                )
                                            }
                                        }
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = item.subtitle,
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    color = TextSecondary,
                                                    fontSize = 11.sp,
                                                    lineHeight = 14.sp
                                                ),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.weight(1f, fill = false)
                                            )
                                            if (item.mentor != null) {
                                                Text(
                                                    text = "• ${item.mentor.name}",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = item.mentor.accentColor,
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.SemiBold
                                                    ),
                                                    maxLines = 1
                                                )
                                            }
                                        }
                                    }
                                }

                                // Barra de progresso sutil da disciplina
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(SurfaceElevated)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(progFraction.coerceAtLeast(if ((prog?.first ?: 0) > 0) 0.05f else 0f))
                                            .fillMaxHeight()
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(item.accentColor)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // ========================================================
        // 3.5. MENTORES DO SABER: COMPANHEIROS PEDAGÓGICOS DA LORE
        // ========================================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("bento_mentors_hub"),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(PrimaryViolet)
                    )
                    Text(
                        text = "MENTORES DO SABER",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary,
                            letterSpacing = 0.8.sp,
                            fontSize = 11.sp
                        )
                    )
                }

                Text(
                    text = "Toque para conselhos",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextTertiary,
                        fontSize = 11.sp
                    )
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(KnowverseLoreRepository.ALL_CHARACTERS) { mentor ->
                    Surface(
                        color = ObsidianSurface,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier
                            .width(136.dp)
                            .clickable { selectedMentorForDetail = mentor }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(mentor.accentColor.copy(alpha = 0.12f))
                                    .border(1.dp, mentor.accentColor.copy(alpha = 0.5f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = mentor.drawableRes),
                                    contentDescription = mentor.name,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Text(
                                text = mentor.name,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = mentor.title,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = mentor.accentColor,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Surface(
                                color = SurfaceElevated,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = mentor.badgeLabel,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextTertiary,
                                        fontSize = 9.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        // ========================================================
        // 4. DESTAQUE DEDICADO: LABORATÓRIO DE REDAÇÃO ENEM
        // ========================================================
        Surface(
            color = ObsidianSurface,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToEssayLab() }
                .testTag("bento_essay_lab")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(SuccessEmerald.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = SuccessEmerald,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "Laboratório de Redação ENEM",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontSize = 13.5.sp
                            )
                        )
                    }

                    Surface(
                        color = SuccessEmerald.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "Matriz INEP",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SuccessEmerald,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.5.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    text = "Escreva e receba diagnóstico detalhado nas 5 competências com critérios oficiais de intervenção e repertório sociocultural.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 11.5.sp,
                        lineHeight = 15.sp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Competências C1 a C5 • Detalhamento",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TextTertiary,
                            fontSize = 10.sp
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Escrever redação",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SuccessEmerald,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = SuccessEmerald,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }

        // ========================================================
        // 5. BANCO DE EXERCÍCIOS & RANKING GLOBAL (DUO DE AÇÕES)
        // ========================================================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // [Card Esquerda] Banco de Exercícios Adaptativo
            Surface(
                color = ObsidianSurface,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .weight(1f)
                    .height(115.dp)
                    .clickable { onNavigateToExercises() }
                    .testTag("bento_exercises_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFF59E0B).copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = Color(0xFFF59E0B),
                                modifier = Modifier.size(15.dp)
                            )
                        }

                        Text(
                            text = "Treino",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFFF59E0B),
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp
                            )
                        )
                    }

                    Column {
                        Text(
                            text = "Exercícios",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontSize = 13.sp
                            )
                        )
                        Text(
                            text = "Questões com gabarito comentado",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontSize = 9.5.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = "Praticar",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFFF59E0B),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.5.sp
                            )
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFFF59E0B),
                            modifier = Modifier.size(11.dp)
                        )
                    }
                }
            }

            // [Card Direita] Ranking Global & XP
            Surface(
                color = ObsidianSurface,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .weight(1f)
                    .height(115.dp)
                    .clickable { onNavigateToRanking() }
                    .testTag("bento_ranking_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(GoldXp.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(15.dp)
                            )
                        }

                        Text(
                            text = "$totalXp XP",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GoldXp,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        )
                    }

                    Column {
                        Text(
                            text = "#$userRankPosition no Ranking",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontSize = 13.sp
                            )
                        )
                        Text(
                            text = "Liga Semanal de Estudantes",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontSize = 9.5.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = "Ver tabela",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextTertiary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.5.sp
                            )
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = TextTertiary,
                            modifier = Modifier.size(11.dp)
                        )
                    }
                }
            }
        }

        // ========================================================
        // MODAL DE DETALHES & CONSELHO DO MENTOR
        // ========================================================
        selectedMentorForDetail?.let { mentor ->
            Dialog(onDismissRequest = { selectedMentorForDetail = null }) {
                Surface(
                    color = ObsidianSurface,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, mentor.accentColor.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(mentor.accentColor.copy(alpha = 0.15f))
                                        .border(1.5.dp, mentor.accentColor, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = mentor.drawableRes),
                                        contentDescription = mentor.name,
                                        modifier = Modifier
                                            .size(44.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Column {
                                    Text(
                                        text = mentor.name,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = TextPrimary,
                                            fontSize = 16.sp
                                        )
                                    )
                                    Text(
                                        text = "${mentor.title} • ${mentor.species}",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = mentor.accentColor,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 11.5.sp
                                        )
                                    )
                                }
                            }

                            Surface(
                                color = SurfaceElevated,
                                shape = CircleShape,
                                modifier = Modifier.clickable { selectedMentorForDetail = null }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Fechar",
                                    tint = TextSecondary,
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .size(16.dp)
                                )
                            }
                        }

                        // Lore Backstory
                        Surface(
                            color = SurfaceElevated,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "SOBRE O MENTOR",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextTertiary,
                                        fontSize = 9.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 0.6.sp
                                    )
                                )
                                Text(
                                    text = mentor.lore,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        fontSize = 11.5.sp,
                                        lineHeight = 15.sp
                                    )
                                )
                            }
                        }

                        // Pedagogical Advice Quote
                        Surface(
                            color = mentor.accentColor.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, mentor.accentColor.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = mentor.accentColor,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = "DICA DO MENTOR",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = mentor.accentColor,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.5.sp,
                                            letterSpacing = 0.5.sp
                                        )
                                    )
                                }
                                Text(
                                    text = "\"${mentor.tips.firstOrNull() ?: mentor.philosophy}\"",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 11.5.sp,
                                        lineHeight = 15.sp
                                    )
                                )
                            }
                        }

                        // Disciplines handled
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Surface(
                                color = SurfaceElevated,
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, BorderSubtle)
                            ) {
                                Text(
                                    text = mentor.badgeLabel,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = mentor.accentColor,
                                        fontSize = 9.5.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                )
                            }
                            Surface(
                                color = SurfaceElevated,
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = mentor.domain,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextSecondary,
                                        fontSize = 9.5.sp,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Button(
                            onClick = {
                                selectedMentorForDetail = null
                                onNavigateToTrail()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = mentor.accentColor),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Estudar com ${mentor.name}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.5.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

private data class SubjectItem(
    val title: String,
    val subtitle: String,
    val accentColor: Color,
    val icon: ImageVector,
    val progress: Pair<Int, Int>? = null,
    val mentor: KnowverseCharacter? = null,
    val onClick: () -> Unit
)

package com.example.ui.components.home

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.UserProgress
import com.example.data.python.PythonCurriculum
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.util.ShareUtil

/**
 * Widgets Secundários da Home:
 * 1. Avaliação de Módulo (Teste Final Kahoot interativo por nível de dificuldade com bloqueio/desbloqueio).
 * 2. Convidar amigos / Compartilhar Knowverse em formato limpo e horizontal.
 */
@Composable
fun GamificationWidgets(
    userProgress: UserProgress,
    onStartFinalTest: (KahootModule) -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    var selectedTier by remember { mutableStateOf(DifficultyTier.BASIC) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Título de Seção
        Text(
            text = "AVALIAÇÃO & COMUNIDADE",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextTertiary,
                letterSpacing = 1.sp,
                fontSize = 10.sp
            )
        )

        // ========================================================
        // 1. CARD DE TESTE FINAL DO MÓDULO COM SELETOR COMPACTO
        // ========================================================
        Surface(
            color = ObsidianSurface,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("gamification_final_test_widget")
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Header com Título e Seletor Segmentado de Tier
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(GoldXp.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Text(
                            text = "Teste do Módulo",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }

                    // Seletor Segmentado Minimalista (Básico, Médio, Avançado)
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, BorderSubtle)
                    ) {
                        Row(
                            modifier = Modifier.padding(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            listOf(
                                DifficultyTier.BASIC to "Básico",
                                DifficultyTier.INTERMEDIATE to "Médio",
                                DifficultyTier.ADVANCED to "Avançado"
                            ).forEach { (tier, label) ->
                                val isSelected = selectedTier == tier
                                Surface(
                                    color = if (isSelected) PrimaryViolet else Color.Transparent,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.clickable { selectedTier = tier }
                                ) {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 10.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) Color.White else TextSecondary
                                        ),
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // Cálculo do estado de desbloqueio do teste selecionado
                val activeTest = when (selectedTier) {
                    DifficultyTier.BASIC -> PythonCurriculum.basicKahoot
                    DifficultyTier.INTERMEDIATE -> PythonCurriculum.intermediateKahoot
                    DifficultyTier.ADVANCED -> PythonCurriculum.advancedKahoot
                }
                val isTierUnlocked = userProgress.isTierUnlocked(selectedTier)
                val tierLessons = PythonCurriculum.getUnitsForTier(selectedTier).flatMap { it.lessons }
                val allTierLessonsDone = tierLessons.isNotEmpty() && tierLessons.all { userProgress.completedLessonIds.contains(it.id) }
                val isTestPlayable = isTierUnlocked && allTierLessonsDone

                Spacer(modifier = Modifier.height(10.dp))

                // Área clicável de inicialização do teste
                Surface(
                    color = SurfaceCard,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, if (isTestPlayable) PrimaryViolet.copy(alpha = 0.35f) else BorderSubtle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = isTestPlayable) { onStartFinalTest(activeTest) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = activeTest.title,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (isTestPlayable) TextPrimary else TextTertiary
                                    )
                                )

                                if (!isTestPlayable) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Bloqueado",
                                        tint = TextTertiary,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }

                            Text(
                                text = if (isTestPlayable) "Avaliação pronta. Clique para iniciar o desafio!" else "Complete todas as lições do módulo para desbloquear.",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (isTestPlayable) TextSecondary else TextTertiary,
                                    fontSize = 10.5.sp
                                )
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Abrir teste",
                            tint = if (isTestPlayable) PrimaryViolet else TextTertiary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // ========================================================
        // 2. ATALHO COMPACTO DE COMPARTILHAMENTO / CONVITE
        // ========================================================
        Surface(
            color = ObsidianSurface,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { ShareUtil.shareApp(context) }
                .testTag("gamification_share_app_card")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(PrimaryViolet.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartilhar",
                            tint = PrimaryViolet,
                            modifier = Modifier.size(15.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Convidar Colegas de Estudo",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary,
                                fontSize = 12.sp
                            )
                        )
                        Text(
                            text = "Compartilhe o Knowverse e estude em comunidade",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontSize = 10.5.sp
                            )
                        )
                    }
                }

                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, BorderSubtle)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Compartilhar",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PrimaryViolet,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }
        }
    }
}

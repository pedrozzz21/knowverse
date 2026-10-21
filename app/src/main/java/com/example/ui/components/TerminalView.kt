package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CodeDefault
import com.example.ui.theme.CodeTextStyle
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TerminalBg
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun TerminalView(
    stdout: String,
    errorText: String? = null,
    executionTimeMs: Long = 0,
    isRunning: Boolean = false,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .border(1.dp, BorderSubtle, RoundedCornerShape(6.dp))
            .background(TerminalBg)
    ) {
        // Linear/Vercel style terminal header
        Surface(
            color = SurfaceCard,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(BorderSubtle))
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(BorderSubtle))
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(BorderSubtle))
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "terminal",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary
                        )
                    )

                    if (executionTimeMs > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${executionTimeMs}ms",
                            style = CodeTextStyle.copy(
                                fontSize = 11.sp,
                                color = TextTertiary
                            )
                        )
                    }
                }

                if (stdout.isNotEmpty() || errorText != null) {
                    IconButton(
                        onClick = {
                            val fullOutput = buildString {
                                if (stdout.isNotEmpty()) append(stdout)
                                if (errorText != null) {
                                    if (isNotEmpty()) append("\n")
                                    append(errorText)
                                }
                            }
                            clipboardManager.setText(AnnotatedString(fullOutput))
                        },
                        modifier = Modifier
                            .size(26.dp)
                            .testTag("copy_terminal_output_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ContentCopy,
                            contentDescription = "Copiar saída",
                            tint = TextTertiary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }

        // Terminal Console Body
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .verticalScroll(scrollState)
                .padding(10.dp)
        ) {
            when {
                isRunning -> {
                    Text(
                        text = "Executando no interpretador Python 3...",
                        style = CodeTextStyle.copy(
                            color = TextSecondary,
                            fontSize = 12.5.sp
                        )
                    )
                }

                stdout.isEmpty() && errorText == null -> {
                    Text(
                        text = "Saída do terminal aparecerá aqui após execução.",
                        style = CodeTextStyle.copy(
                            color = TextTertiary,
                            fontSize = 12.sp
                        )
                    )
                }

                else -> {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        if (stdout.isNotEmpty()) {
                            Text(
                                text = stdout,
                                style = CodeTextStyle.copy(
                                    color = CodeDefault,
                                    fontSize = 12.5.sp,
                                    lineHeight = 18.sp
                                )
                            )
                        }

                        if (errorText != null) {
                            Text(
                                text = errorText,
                                style = CodeTextStyle.copy(
                                    color = ErrorRose,
                                    fontSize = 12.5.sp,
                                    lineHeight = 18.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

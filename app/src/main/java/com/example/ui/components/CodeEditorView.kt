package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderActive
import com.example.ui.theme.BorderGlow
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CodeDefault
import com.example.ui.theme.CodeTextStyle
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TerminalBg
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun CodeEditorView(
    codeValue: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    highlightLine: Int = -1
) {
    val clipboardManager = LocalClipboardManager.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = codeValue, selection = TextRange(codeValue.length)))
    }

    LaunchedEffect(codeValue) {
        if (codeValue != textFieldValue.text) {
            val targetCursor = codeValue.length.coerceAtLeast(0)
            textFieldValue = TextFieldValue(text = codeValue, selection = TextRange(targetCursor))
        }
    }

    val quickSymbols = listOf(
        "    ", ":", "(", ")", "\"", "'", "=", "+", "-", "*", "/", "[", "]", "{", "}", "def ", "class ", "return "
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = if (isFocused) BorderActive else BorderSubtle,
                shape = RoundedCornerShape(6.dp)
            )
            .background(ObsidianSurface)
    ) {
        // Linear/GitHub style compact code header
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
                    Icon(
                        imageVector = KnowverseIcons.Python,
                        contentDescription = "Python",
                        tint = TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "main.py",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                    )
                    if (highlightLine > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "L$highlightLine",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = ErrorRose,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(
                        onClick = {
                            val text = textFieldValue.text
                            val start = textFieldValue.selection.start
                            val end = textFieldValue.selection.end
                            if (start != end) {
                                val newText = text.removeRange(start.coerceAtLeast(0), end.coerceAtMost(text.length))
                                textFieldValue = TextFieldValue(newText, TextRange(start))
                                onCodeChange(newText)
                            } else if (start > 0) {
                                val newText = text.removeRange(start - 1, start)
                                textFieldValue = TextFieldValue(newText, TextRange(start - 1))
                                onCodeChange(newText)
                            }
                            focusRequester.requestFocus()
                        },
                        modifier = Modifier.size(28.dp).testTag("backspace_code_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Backspace,
                            contentDescription = "Apagar",
                            tint = TextSecondary,
                            modifier = Modifier.size(15.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            textFieldValue = TextFieldValue("", TextRange.Zero)
                            onCodeChange("")
                            focusRequester.requestFocus()
                        },
                        modifier = Modifier.size(28.dp).testTag("clear_code_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = "Limpar",
                            tint = TextSecondary,
                            modifier = Modifier.size(15.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            clipboardManager.setText(AnnotatedString(textFieldValue.text))
                        },
                        modifier = Modifier.size(28.dp).testTag("copy_code_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ContentCopy,
                            contentDescription = "Copiar",
                            tint = TextSecondary,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }

        // Minimalist quick symbol strip
        Surface(
            color = ObsidianSurface,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 0.5.dp, color = BorderSubtle)
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(quickSymbols) { sym ->
                    val label = if (sym == "    ") "Tab" else sym
                    FilledTonalButton(
                        onClick = {
                            val currentText = textFieldValue.text
                            val selStart = textFieldValue.selection.start.coerceIn(0, currentText.length)
                            val selEnd = textFieldValue.selection.end.coerceIn(0, currentText.length)
                            val newText = currentText.replaceRange(selStart, selEnd, sym)
                            val newCursor = selStart + sym.length
                            textFieldValue = TextFieldValue(
                                text = newText,
                                selection = TextRange(newCursor)
                            )
                            onCodeChange(newText)
                            focusRequester.requestFocus()
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = SurfaceElevated,
                            contentColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                        modifier = Modifier
                            .height(28.dp)
                            .testTag("symbol_btn_${label.trim()}")
                    ) {
                        Text(
                            text = label,
                            style = CodeTextStyle.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = TextPrimary
                            )
                        )
                    }
                }
            }
        }

        // Code Editor Canvas
        val lineCount = textFieldValue.text.split("\n").size.coerceAtLeast(1)
        val lineNumbers = (1..lineCount).joinToString("\n")
        val editorHorizontalScroll = rememberScrollState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    focusRequester.requestFocus()
                }
                .padding(vertical = 6.dp)
        ) {
            // Line numbers column
            Text(
                text = lineNumbers,
                style = CodeTextStyle.copy(
                    color = TextTertiary,
                    fontSize = 12.5.sp,
                    lineHeight = 20.sp
                ),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .width(34.dp)
                    .background(TerminalBg)
                    .padding(end = 6.dp, start = 4.dp)
            )

            // Divider vertical line
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(BorderSubtle)
            )

            // Text editing area with horizontal scroll and placeholder
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .horizontalScroll(editorHorizontalScroll)
                    .padding(horizontal = 10.dp)
            ) {
                if (textFieldValue.text.isEmpty()) {
                    Text(
                        text = "# Escreva seu código Python aqui...",
                        style = CodeTextStyle.copy(
                            color = TextTertiary.copy(alpha = 0.5f),
                            lineHeight = 20.sp
                        )
                    )
                }

                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { newValue ->
                        textFieldValue = newValue
                        onCodeChange(newValue.text)
                    },
                    textStyle = CodeTextStyle.copy(
                        color = CodeDefault,
                        lineHeight = 20.sp
                    ),
                    cursorBrush = SolidColor(TextPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusChanged { isFocused = it.isFocused }
                        .testTag("code_input_field")
                )
            }
        }
    }
}

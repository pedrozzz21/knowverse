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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderGlow
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.PrimaryVioletLight
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

sealed class MarkdownBlock {
    data class Heading1(val text: String) : MarkdownBlock()
    data class Heading2(val text: String) : MarkdownBlock()
    data class Heading3(val text: String) : MarkdownBlock()
    data class BlockQuote(val text: String) : MarkdownBlock()
    data class ListItem(val text: String) : MarkdownBlock()
    data class NumberedItem(val number: String, val text: String) : MarkdownBlock()
    data class Table(val rows: List<List<String>>) : MarkdownBlock()
    data class Paragraph(val text: String) : MarkdownBlock()
}

/**
 * Renderizador de Markdown Estruturado de alta fidelidade para o feedback pedagógico do INEP.
 */
@Composable
fun StructuredMarkdownRenderer(
    markdownText: String,
    modifier: Modifier = Modifier
) {
    val blocks = remember(markdownText) { parseMarkdown(markdownText) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        blocks.forEach { block ->
            when (block) {
                is MarkdownBlock.Heading1 -> MarkdownHeading1(text = block.text)
                is MarkdownBlock.Heading2 -> MarkdownHeading2(text = block.text)
                is MarkdownBlock.Heading3 -> MarkdownHeading3(text = block.text)
                is MarkdownBlock.BlockQuote -> MarkdownBlockQuote(text = block.text)
                is MarkdownBlock.ListItem -> MarkdownListItem(text = block.text)
                is MarkdownBlock.NumberedItem -> MarkdownNumberedItem(number = block.number, text = block.text)
                is MarkdownBlock.Table -> MarkdownTable(rows = block.rows)
                is MarkdownBlock.Paragraph -> MarkdownParagraph(text = block.text)
            }
        }
    }
}

private fun parseMarkdown(text: String): List<MarkdownBlock> {
    val lines = text.lines()
    val blocks = mutableListOf<MarkdownBlock>()
    val currentTableRows = mutableListOf<List<String>>()

    fun flushTable() {
        if (currentTableRows.isNotEmpty()) {
            blocks.add(MarkdownBlock.Table(currentTableRows.toList()))
            currentTableRows.clear()
        }
    }

    for (line in lines) {
        val trimmed = line.trim()

        if (trimmed.startsWith("|") && trimmed.endsWith("|")) {
            val cells = trimmed.split("|")
                .map { it.trim() }
                .filterIndexed { index, _ -> index > 0 && index < trimmed.split("|").size - 1 }

            val isDivider = cells.all { it.all { char -> char == '-' || char == ':' } }
            if (!isDivider && cells.isNotEmpty()) {
                currentTableRows.add(cells)
            }
            continue
        } else {
            flushTable()
        }

        if (trimmed.isEmpty()) continue

        when {
            trimmed.startsWith("# ") -> {
                blocks.add(MarkdownBlock.Heading1(trimmed.removePrefix("# ").trim()))
            }
            trimmed.startsWith("## ") -> {
                blocks.add(MarkdownBlock.Heading2(trimmed.removePrefix("## ").trim()))
            }
            trimmed.startsWith("### ") -> {
                blocks.add(MarkdownBlock.Heading3(trimmed.removePrefix("### ").trim()))
            }
            trimmed.startsWith("> ") -> {
                blocks.add(MarkdownBlock.BlockQuote(trimmed.removePrefix("> ").trim()))
            }
            trimmed.startsWith("- ") || trimmed.startsWith("* ") -> {
                blocks.add(MarkdownBlock.ListItem(trimmed.substring(2).trim()))
            }
            trimmed.matches(Regex("^\\d+\\.\\s+.*")) -> {
                val number = trimmed.substringBefore(".")
                val content = trimmed.substringAfter(".").trim()
                blocks.add(MarkdownBlock.NumberedItem(number, content))
            }
            else -> {
                blocks.add(MarkdownBlock.Paragraph(trimmed))
            }
        }
    }

    flushTable()
    return blocks
}

@Composable
private fun MarkdownHeading1(text: String) {
    Surface(
        color = SurfaceElevated,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderGlow, RoundedCornerShape(12.dp))
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(PrimaryViolet.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = GoldXp,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
private fun MarkdownHeading2(text: String) {
    Column(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(18.dp)
                    .background(PrimaryViolet, RoundedCornerShape(2.dp))
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 16.sp
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 6.dp),
            thickness = 1.dp,
            color = BorderSubtle
        )
    }
}

@Composable
private fun MarkdownHeading3(text: String) {
    Row(
        modifier = Modifier.padding(top = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Lightbulb,
            contentDescription = null,
            tint = PrimaryVioletLight,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun MarkdownBlockQuote(text: String) {
    Surface(
        color = ObsidianSurface,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(28.dp)
                    .background(GoldXp, RoundedCornerShape(2.dp))
            )
            Text(
                text = renderFormattedText(text),
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun MarkdownListItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 7.dp)
                .size(6.dp)
                .clip(CircleShape)
                .background(PrimaryViolet)
        )
        Text(
            text = renderFormattedText(text),
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun MarkdownNumberedItem(number: String, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            color = SurfaceCard,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(top = 2.dp)
        ) {
            Text(
                text = "$number.",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryVioletLight,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
            )
        }
        Text(
            text = renderFormattedText(text),
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun MarkdownParagraph(text: String) {
    Text(
        text = renderFormattedText(text),
        style = MaterialTheme.typography.bodyMedium,
        color = TextPrimary,
        lineHeight = 22.sp,
        modifier = Modifier.padding(horizontal = 2.dp)
    )
}

@Composable
private fun MarkdownTable(rows: List<List<String>>) {
    if (rows.isEmpty()) return

    Surface(
        color = ObsidianSurface,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            val header = rows.first()
            val dataRows = rows.drop(1)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceCard, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                header.forEachIndexed { idx, colText ->
                    Text(
                        text = colText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = GoldXp,
                        modifier = Modifier.weight(if (idx == 0) 1.2f else if (idx == 1) 0.6f else 1.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            dataRows.forEachIndexed { rIdx, row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    row.forEachIndexed { cIdx, cellText ->
                        val isScoreCol = cIdx == 1 && cellText.toIntOrNull() != null
                        if (isScoreCol) {
                            val scoreVal = cellText.toIntOrNull() ?: 0
                            Surface(
                                color = if (scoreVal >= 160) SuccessEmerald.copy(alpha = 0.2f) else PrimaryViolet.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "$cellText pts",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (scoreVal >= 160) SuccessEmerald else PrimaryVioletLight,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        } else {
                            Text(
                                text = renderFormattedText(cellText),
                                style = MaterialTheme.typography.bodySmall,
                                color = if (cIdx == 0) TextPrimary else TextSecondary,
                                fontWeight = if (cIdx == 0) FontWeight.SemiBold else FontWeight.Normal,
                                modifier = Modifier.weight(if (cIdx == 0) 1.2f else if (cIdx == 1) 0.6f else 1.8f)
                            )
                        }
                    }
                }
                if (rIdx < dataRows.size - 1) {
                    HorizontalDivider(color = BorderSubtle.copy(alpha = 0.5f), thickness = 0.5.dp)
                }
            }
        }
    }
}

private fun renderFormattedText(raw: String) = buildAnnotatedString {
    var cursor = 0
    val length = raw.length

    while (cursor < length) {
        val boldStart = raw.indexOf("**", cursor)
        val codeStart = raw.indexOf("`", cursor)

        val nextSpecial = listOfNotNull(
            if (boldStart != -1) boldStart else null,
            if (codeStart != -1) codeStart else null
        ).minOrNull()

        if (nextSpecial == null) {
            append(raw.substring(cursor))
            break
        }

        if (nextSpecial > cursor) {
            append(raw.substring(cursor, nextSpecial))
        }

        if (nextSpecial == boldStart) {
            val boldEnd = raw.indexOf("**", boldStart + 2)
            if (boldEnd != -1) {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = TextPrimary)) {
                    append(raw.substring(boldStart + 2, boldEnd))
                }
                cursor = boldEnd + 2
            } else {
                append("**")
                cursor = boldStart + 2
            }
        } else if (nextSpecial == codeStart) {
            val codeEnd = raw.indexOf("`", codeStart + 1)
            if (codeEnd != -1) {
                withStyle(
                    SpanStyle(
                        fontFamily = FontFamily.Monospace,
                        color = GoldXp,
                        background = SurfaceCard
                    )
                ) {
                    append(raw.substring(codeStart + 1, codeEnd))
                }
                cursor = codeEnd + 1
            } else {
                append("`")
                cursor = codeStart + 1
            }
        }
    }
}

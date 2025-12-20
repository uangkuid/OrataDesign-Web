package com.oratakashi.design.docs.ui.component.code

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.BoldHighlight
import dev.snipme.highlights.model.CodeHighlight
import dev.snipme.highlights.model.ColorHighlight

@Composable
fun CodeTextView(
    modifier: Modifier = Modifier.background(Color.Transparent),
    highlights: Highlights
) {
    var textState by remember {
        mutableStateOf(AnnotatedString(highlights.getCode()))
    }

    LaunchedEffect(highlights) {
        textState = highlights
            .getHighlights()
            .generateAnnotatedString(highlights.getCode())
    }

    SelectionContainer {
        Text(
            modifier = modifier,
            text = textState
        )
    }
}

internal const val TAB_LENGTH = 4
internal const val TAB_CHAR = "\t"

fun List<CodeHighlight>.generateAnnotatedString(code: String) =
    buildAnnotatedString {
        append(code)

        forEach {
            when (it) {
                is BoldHighlight -> addStyle(
                    SpanStyle(fontWeight = FontWeight.Bold),
                    start = it.location.start,
                    end = it.location.end,
                )

                is ColorHighlight -> addStyle(
                    SpanStyle(color = Color(it.rgb).copy(alpha = 1f)),
                    start = it.location.start,
                    end = it.location.end,
                )
            }
        }
    }


internal fun TextFieldValue.updateIndentations(handleIndentations: Boolean) =
    if (handleIndentations && text.contains(TAB_CHAR)) {
        val result = text.replace(TAB_CHAR, " ".repeat(TAB_LENGTH))
        this.copy(text = result, TextRange(selection.start + TAB_LENGTH - 1))
    } else {
        this
    }

internal fun TextFieldValue.copySpanStyles(source: TextFieldValue) =
    this.copy(
        annotatedString = buildAnnotatedString {
            append(text)
            source.annotatedString.spanStyles.forEach {
                if (it.start >= 0 && it.end > it.start && it.end < text.length) {
                    addStyle(it.item, it.start, it.end)
                }
            }
        }
    )
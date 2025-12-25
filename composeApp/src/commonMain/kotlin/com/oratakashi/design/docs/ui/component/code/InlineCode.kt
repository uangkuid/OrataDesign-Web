package com.oratakashi.design.docs.ui.component.code

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.oratakashi.design.foundation.OrataTheme
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes

@Composable
fun InlineCode(
    code: String = "",
    language: SyntaxLanguage = SyntaxLanguage.DEFAULT,
    modifier: Modifier = Modifier
) {
    val highlights = remember(code, language) {
        Highlights
            .Builder(
                code = code.trimIndent(),
                theme = SyntaxThemes.pastel(darkMode = true),
                language = language
            )
            .build()
    }

    CodeTextView(
        highlights = highlights,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = OrataTheme.colors.surfaceContainer)
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
    )
}
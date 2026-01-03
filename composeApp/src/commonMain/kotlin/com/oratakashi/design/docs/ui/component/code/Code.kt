package com.oratakashi.design.docs.ui.component.code

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.oratakashi.design.component.button.OraButtonSize
import com.oratakashi.design.component.button.OraTransparentButton
import com.oratakashi.design.foundation.OrataTheme
import com.oratakashi.design.docs.helpers.ClipboardHelpers
import compose.icons.FeatherIcons
import compose.icons.feathericons.Copy
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes

@Composable
fun Code(
    fileName: String = "",
    code: String = "",
    language: SyntaxLanguage = SyntaxLanguage.DEFAULT,
    canExpand: Boolean = true
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
    var expanded by remember { mutableStateOf(false) }
    val minCollapsedHeight = 120.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(fileName.isNotBlank()) {
                    Text(
                        text = fileName,
                        style = OrataTheme.typography.bodyMedium(),
                        color = OrataTheme.colors.onSurface
                    )
                }

                OraTransparentButton(
                    onClick = {
                        ClipboardHelpers.copyToClipboard(code.trimIndent())
                    },
                    size = OraButtonSize.XSmall,
                    label = "Copy",
                    iconLeft = {
                        Icon(imageVector = FeatherIcons.Copy, null)
                    }
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF3F3F46)
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                CodeTextView(
                    highlights = highlights,
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (canExpand && !expanded) {
                                Modifier.heightIn(max = minCollapsedHeight)
                            } else {
                                Modifier.wrapContentHeight()
                            }
                        )
                        .padding(16.dp)
                )

                // Gradient Overlay saat collapsed
                if (code.isNotBlank() && canExpand && !expanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        OrataTheme.colors.surfaceContainerHigh
                                    )
                                )
                            )
                    )
                }
            }

            // Show/Hide Button
            if (code.isNotBlank() && canExpand) {

                AnimatedVisibility(expanded) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                }

                OraTransparentButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    label = if (expanded) "Hide code" else "Show code",
                    size = OraButtonSize.Small
                )
            }
        }
    }
}
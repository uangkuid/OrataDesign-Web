package com.oratakashi.design.docs.ui.screen.content.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.navigation.page.ConfigurationNavigation
import com.oratakashi.design.docs.ui.component.code.Code
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import oratadesign_web.composeapp.generated.resources.Res

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = ConfigurationNavigation
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            item(
                key = "configure_colors"
            ) {
                ContentSection(
                    title = "Configure Colors",
                    content = {
                        Text("Orata Design is built on Material Expressive, making it immediately familiar to Android developers who already work with modern Material principles.")

                        Text("In addition to its visual language, Orata Design fully adopts the latest Material Expressive color schema principles. This ensures that color usage across the system is intentional, accessible, and emotionally expressive, while still maintaining clarity and consistency. By leveraging these established guidelines, Orata enables developers and designers to create interfaces that feel modern, cohesive, and aligned with current Material standards across all supported platforms.")

                        var bytesColorsCode by remember {
                            mutableStateOf(ByteArray(0))
                        }

                        LaunchedEffect(Unit) {
                            bytesColorsCode = Res.readBytes("files/configuration/colors.kt")
                        }

                        Code(
                            fileName = "ColorSchema.kt",
                            code = bytesColorsCode.decodeToString(),
                            canExpand = true
                        )
                    }
                )
            }

            item(
                key = "configure_typography"
            ) {
                ContentSection(
                    title = "Configure Typography",
                    content = {
                        Text("Similar to the color scheme approach, Orata Design also adopts typography tokens and typographic principles from the latest Material Expressive specification.")

                        Text("By aligning with these established standards, Orata ensures that text styles, hierarchy, and readability behave in ways that feel natural and familiar to Android developers. This consistency reduces the learning curve while preserving flexibility, allowing teams to build interfaces that are both expressive and highly readable across all platforms supported by Orata Design.")

                        var bytesTypographyCode by remember {
                            mutableStateOf(ByteArray(0))
                        }

                        LaunchedEffect(Unit) {
                            bytesTypographyCode = Res.readBytes("files/configuration/typography.kt")
                        }

                        Code(
                            fileName = "Typography.kt",
                            code = bytesTypographyCode.decodeToString(),
                            canExpand = true
                        )
                    }
                )
            }

            item(
                key = "configure_theme"
            ) {
                ContentSection(
                    title = "Configure Theme",
                    content = {
                        Text("After configuring your color scheme and typography, the next required step is to set up the application theme.")

                        Text("Orata Design follows a theming approach that is intentionally similar to the standard Material Theme configuration, ensuring a smooth and familiar experience for Android developers. To apply the theme, you only need to create a composable function that wraps your application with `OrataAppTheme`, providing the color and typography configurations that represent your app’s visual identity.")

                        Text("This centralized theme configuration allows Orata to consistently propagate design tokens—such as colors and typography—throughout the entire UI, ensuring visual coherence, scalability, and ease of maintenance across all supported platforms.")

                        var bytesThemeCode by remember {
                            mutableStateOf(ByteArray(0))
                        }

                        LaunchedEffect(Unit) {
                            bytesThemeCode = Res.readBytes("files/configuration/theme.kt")
                        }

                        Code(
                            fileName = "Theme.kt",
                            code = bytesThemeCode.decodeToString(),
                            canExpand = true
                        )
                    }
                )
            }
        }
    }
}
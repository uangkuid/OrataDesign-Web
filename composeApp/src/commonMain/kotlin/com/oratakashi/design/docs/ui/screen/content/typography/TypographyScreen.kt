package com.oratakashi.design.docs.ui.screen.content.typography

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.navigation.page.TypographyNavigation
import com.oratakashi.design.docs.ui.component.code.InlineCode
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.foundation.OrataTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable
import dev.snipme.highlights.model.SyntaxLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypographyScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = TypographyNavigation
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            item(
                key = "type_scale_token"
            ) {
                ContentSection(
                    title = "Type scale tokens",
                    content = {
                        Text("Orata Design follows a type scale and typography role system that closely mirrors Material Expressive. This approach is intentionally designed to help developers adapt quickly, without requiring them to relearn typography concepts from the ground up when adopting Orata Design.")
                        Text("By aligning its typography structure with Material Design, Orata allows developers to continue using familiar Material components while applying the Orata Design theme as an additional theming layer. Under the hood, the Orata Theme automatically configures and maps typography values to the corresponding Material typography system, ensuring consistency and correctness across all components.")
                        Text("This compatibility enables a smooth transition from Material-based projects, allowing teams to build expressive, readable, and well-structured interfaces while maintaining the flexibility and scalability required for production environments.")
                    }
                )
            }

            item(
                key = "display"
            ) {
                ContentSection(
                    title = "Display",
                    content = {
                        Text("The default type scale defines three display styles: Large, Medium, and Small. As the most prominent text styles in the hierarchy, display styles are intended for short, high-impact content such as key messages, headlines, or numerals. They are best suited for use on larger screens, where their scale and visual presence can be fully appreciated without compromising readability.")
                        Text("When working with display typography, it is recommended to select a more expressive font style, such as a handwritten or script typeface, to enhance visual character and emotional impact. These styles help establish a strong first impression and reinforce the personality of the interface.")
                        Text("Where supported by the typeface, you should also configure the appropriate optical size based on the intended usage. Proper optical sizing ensures that letterforms remain well-balanced and legible at larger sizes, resulting in a more refined and professional typographic appearance.")

                        DataTable(
                            columns = listOf(
                                DataColumn {
                                    Text(
                                        "Preview",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Token Name",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Font Size",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Font Weight",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                            ),
                            rowHeight = Dp.Unspecified,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Display Large",
                                        style = OrataTheme.typography.displayLarge(),
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "OrataTheme.typography.displayLarge()",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "57.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "64.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }
                            }
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Display Medium",
                                        style = OrataTheme.typography.displayMedium(),
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "OrataTheme.typography.displayMedium ()",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "45.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "52.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }
                            }
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Display Small",
                                        style = OrataTheme.typography.displaySmall(),
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "OrataTheme.typography.displaySmall()",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "36.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "44.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }
                            }
                        }
                    }
                )
            }

            item(
                key = "headline"
            ) {
                ContentSection(
                    title = "Headline",
                    content = {
                        Text("Headline styles are best suited for short, high-emphasis text on smaller screens, where clarity and visual hierarchy are especially important. These styles work well for highlighting primary passages of text, section titles, or key content regions, helping users quickly understand the structure and focus of the interface.")
                        Text("Headlines may also leverage expressive typefaces to reinforce brand personality and visual character. However, to preserve readability and usability, it is essential to apply appropriate line height and letter spacing adjustments. Proper typographic spacing ensures that expressive fonts remain legible and balanced across different screen sizes and platforms.")

                        DataTable(
                            columns = listOf(
                                DataColumn {
                                    Text(
                                        "Preview",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Token Name",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Font Size",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Font Weight",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                            ),
                            rowHeight = Dp.Unspecified,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Headline Large",
                                        style = OrataTheme.typography.headlineLarge(),
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "OrataTheme.typography.headlineLarge()",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "32.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "40.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }
                            }
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Headline Medium",
                                        style = OrataTheme.typography.headlineMedium(),
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "OrataTheme.typography.headlineMedium()",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "28.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "36.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }
                            }
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Headline Small",
                                        style = OrataTheme.typography.headlineSmall(),
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "OrataTheme.typography.displaySmall()",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "24.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }

                                cell {
                                    InlineCode(
                                        code = "32.dp",
                                        language = SyntaxLanguage.KOTLIN
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

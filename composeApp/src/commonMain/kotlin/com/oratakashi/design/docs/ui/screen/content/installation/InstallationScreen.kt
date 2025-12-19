package com.oratakashi.design.docs.ui.screen.content.installation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.component.alert.OraInfoAlert
import com.oratakashi.design.docs.navigation.page.InstallationNavigation
import com.oratakashi.design.docs.ui.component.code.Code
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstallationScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    DetailContent(
        scrollBehavior = scrollBehavior,
        showBack = showBack,
        onBackClick = onBackClick,
        navigation = InstallationNavigation
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            item(
                key = "Prequisites"
            ) {
                ContentSection(
                    title = "Prequisites",
                    content = {
                        OraInfoAlert(
                            title = "Development Status",
                            description = "Orata Design System is in an early, experimental stage and is not even considered \"unstable\" yet. However, it is available to try and explore—feedback and experimentation are welcome!",
                        )

                        Text("If you want clone this project or install SDK in your project, please make sure you Already have at least these below:")

                        DataTable(
                            columns = listOf(
                                DataColumn {
                                    Text("Platform / Tool")
                                },
                                DataColumn {
                                    Text("Requirement")
                                },
                            ),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            row {
                                onClick = {  }
                                cell {
                                    Text(
                                        text = "Gradle"
                                    )
                                }
                                cell {
                                    Text(
                                        text = "Version 8.2 or newer"
                                    )
                                }
                            }
                            row {
                                onClick = { }
                                cell {
                                    Text(
                                        "Java Development Kit (JDK)"
                                    )
                                }
                                cell {
                                    Text(
                                        "Version 17"
                                    )
                                }
                            }
                            row {
                                onClick = { }
                                cell {
                                    Text(
                                        "Android"
                                    )
                                }
                                cell {
                                    Text(
                                        "Minimum SDK 24, Target SDK 34"
                                    )
                                }
                            }
                            row {
                                onClick = { }
                                cell {
                                    Text(
                                        "iOS"
                                    )
                                }
                                cell {
                                    Text(
                                        "iOS 14 or newer"
                                    )
                                }
                            }
                        }
                    }
                )
            }

            item(
                key = "Installation"
            ) {
                ContentSection(
                    title = "Installation",
                    content = {
                        Text("Add UI Kit Dependency on build.gradle")

                        Code(
                            fileName = "build.gradle.kts",
                            code = "implementation(\"com.oratakashi:design:0.0.1-Alpha\")",
                            language = SyntaxLanguage.KOTLIN,
                            canExpand = false
                        )

                        Text("Add Jetifier Support on Project gradle.properties")

                        Code(
                            fileName = "gradle.properties",
                            code = "android.enableJetifier = true;",
                            language = SyntaxLanguage.KOTLIN,
                            canExpand = false
                        )

//                        Box(
//                            modifier = Modifier
//                                .height(800.dp)
//                        ) {
//                            CodeTextView(
//                                highlights = highlights.value
//                            )
//                        }
                    }
                )

            }
        }
    }
}
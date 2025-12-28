package com.oratakashi.design.docs.ui.screen.content.colorsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.navigation.page.ColorSystemNavigation
import com.oratakashi.design.docs.ui.component.color_roles.ColorRoles
import com.oratakashi.design.docs.ui.component.color_roles.ColorRolesPreview
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaPreview
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.foundation.OrataTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronDown
import compose.icons.feathericons.ChevronUp
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSystemScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = ColorSystemNavigation
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            item(
                key = "color_schema"
            ) {
                ContentSection(
                    title = "Color Schema",
                    content = {
                        Text("Orata Design adopts a color scheme and color role structure that closely aligns with Material Expressive. The primary goal of this approach is to enable developers to adapt quickly, without the need to learn an entirely new system from scratch when working with Orata Design.")
                        Text("Because Orata Design follows color schema and role conventions that are familiar from Material Design, developers can continue using existing Material components while applying the Orata Design theme as a layering system. Under the hood, the Orata Theme automatically configures and maps these values to the underlying Material theme, ensuring seamless integration and consistent visual output.")
                        Text("This strategy allows teams to leverage the robustness of Material components while benefiting from Orata’s design language, resulting in a smooth development experience and a consistent, production-ready UI across platforms.")

                        var isDark by remember { mutableStateOf(true) }
                        var isExpanded by remember { mutableStateOf(true) }

                        BoxWithConstraints {
                            val isWideScreen = maxWidth > 300.dp

                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Row {
                                    AnimatedVisibility(isWideScreen) {
                                        PreviewTabs(
                                            tabs = listOf("Dark Mode", "Light Mode"),
                                            selectedTab = "Dark Mode",
                                            onTabSelected = {
                                                isDark = it == "Dark Mode"
                                            }
                                        )
                                    }

                                    Spacer(modifier = Modifier.weight(1f))

                                    val rotation by animateFloatAsState(
                                        targetValue = if (!isExpanded) 180f else 0f,
                                        label = "Chevron Rotation"
                                    )
                                    IconButton(
                                        colors = IconButtonDefaults.iconButtonColors(
                                            containerColor = OrataTheme.colors.surfaceContainer
                                        ),
                                        onClick = {
                                            isExpanded = !isExpanded
                                        }
                                    ) {
                                        Icon(
                                            imageVector = FeatherIcons.ChevronUp,
                                            contentDescription = null,
                                            modifier = Modifier.rotate(rotation)
                                        )
                                    }
                                }

                                AnimatedVisibility(
                                    visible = isExpanded,
                                    enter = slideInVertically() + fadeIn(),
                                    exit = slideOutVertically() + fadeOut()
                                ) {
                                    ColorSchemaPreview(
                                        isDark = isDark
                                    )
                                }
                            }
                        }
                    }
                )
            }

            item(
                key = "general_concept"
            ) {
                ContentSection(
                    title = "General concepts",
                    content = {
                        Text("Below are key terms commonly used in color role naming conventions within Orata Design. Understanding these terms will help you apply colors consistently and correctly across your UI.")

                        DataTable(
                            columns = listOf(
                                DataColumn {
                                    Text(
                                        "Color Roles",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                DataColumn {
                                    Text(
                                        "Description",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            ),
                            rowHeight = Dp.Unspecified,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row {
                                onClick = {

                                }

                                cell {
                                    Text(
                                        text = "Surface",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    Text(
                                        text = "A role intended for backgrounds and large, low-emphasis areas of the interface. Surface colors establish the foundational layers of the UI and support content without drawing unnecessary attention.",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }

                            row {
                                onClick = {

                                }

                                cell {
                                    Text(
                                        text = "Primary, Secondary, Tertiary",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    Text(
                                        text = "Accent color roles used to emphasize or de-emphasize foreground elements. These roles define the visual hierarchy and guide user attention, with Primary typically carrying the strongest emphasis.",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }

                            row {
                                onClick = {

                                }

                                cell {
                                    Text(
                                        text = "Container",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    Text(
                                        text = "Roles designed to be used as fill colors for foreground elements, such as buttons, cards, or other interactive components. Container colors should not be used for text or icons, as they are meant to serve as background fills.",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }

                            row {
                                onClick = {

                                }

                                cell {
                                    Text(
                                        text = "On",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    Text(
                                        text = "Roles prefixed with On indicate colors intended for text or icons placed on top of a corresponding parent color. For example, On Primary is used for text and icons displayed over the Primary fill color, ensuring sufficient contrast and readability.",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }

                            row {
                                onClick = {

                                }

                                cell {
                                    Text(
                                        text = "Variant",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                cell {
                                    Text(
                                        text = "Roles suffixed with Variant represent a lower-emphasis alternative to their non-variant counterparts. For instance, Outline Variant provides a more subtle version of the outline color, suitable for less prominent UI elements.",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                )
            }

            item(
                key = "primary"
            ) {
                ContentSection(
                    title = "Primary",
                    content = {
                        Text("Use Primary roles for the most prominent and attention-grabbing components across the interface, such as Floating Action Buttons (FAB), high-emphasis buttons, and active or selected states. These roles define the core accent color of the UI and play a critical role in guiding user focus.")

                        ColorRolesPreview(
                            roles = ColorRoles.Primary,
                            description = {
                                Text("The Primary color roles are defined as follows:")

                                Text(
                                    text = "Primary",
                                    style = OrataTheme.typography.titleMedium()
                                )

                                Text("Used for high-emphasis fills, text, and icons placed against surface backgrounds. This role represents the main brand or accent color within the interface.")

                                Text(
                                    text = "On Primary",
                                    style = OrataTheme.typography.titleMedium()
                                )

                                Text("Applied to text and icons displayed on top of the Primary color. This role ensures sufficient contrast and clear readability.")

                                Text(
                                    text = "Primary Container",
                                    style = OrataTheme.typography.titleMedium()
                                )

                                Text("A standout fill color used against surface backgrounds, intended for key components such as Floating Action Buttons (FAB) or other high-importance UI elements.")

                                Text(
                                    text = "On Primary Container",
                                    style = OrataTheme.typography.titleMedium()
                                )

                                Text("Used for text and icons placed on top of the Primary Container color, maintaining visual clarity and accessibility.")
                            }
                        )
                    }
                )
            }
        }
    }
}

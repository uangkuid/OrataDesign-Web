package com.oratakashi.design.docs.ui.screen.content.colorsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.navigation.page.ColorSystemNavigation
import com.oratakashi.design.docs.ui.component.colorschema.ColorSchemaPreview
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.foundation.OrataAppTheme

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
                        PreviewTabs(
                            tabs = listOf("Dark Mode", "Light Mode"),
                            selectedTab = "Dark Mode",
                            onTabSelected = {
                                isDark = it == "Dark Mode"
                            }
                        )

                        ColorSchemaPreview(
                            isDark = isDark
                        )
                    }
                )
            }
        }
    }
}

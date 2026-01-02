package com.oratakashi.design.docs.ui.screen.content.alert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.oratakashi.design.component.alert.OraInfoAlert
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = AlertNavigation
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            item(
                key = "preview"
            ) {
                ContentSection(
                    content = {
                        Text("The Alert component is used to present a temporary surface that offers a list of available actions or options. It is designed to display a brief message or notification to the user within the context of an existing activity, without requiring a full screen transition.")

                        Text("Alerts are typically used to communicate important information, request user confirmation, or present time-sensitive choices. By appearing as an overlay on top of the current interface, the Alert component captures user attention while maintaining continuity with the underlying content.")

                        ComponentPreview {
                            OraInfoAlert(
                                title = "Info",
                            )
                        }
                    }
                )
            }
        }
    }
}

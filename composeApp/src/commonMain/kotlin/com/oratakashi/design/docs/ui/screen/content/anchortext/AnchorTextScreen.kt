package com.oratakashi.design.docs.ui.screen.content.anchortext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.docs.ui.templates.alert.Alert
import com.oratakashi.design.docs.ui.templates.anchortext.AnchorText
import com.oratakashi.design.docs.ui.templates.anchortext.AnchorTextConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnchorTextScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var anchorTextData by remember { mutableStateOf(
        AnchorTextConfig(
            text = "Anchor Text"
        )
    ) }

    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = AnchorTextNavigation
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
                        Text("Anchor text refers to clickable text that functions as a hyperlink to another page, section, or external website. It provides users with a clear and descriptive indication of the destination or action associated with the link.")

                        Text("Well-crafted anchor text improves usability and accessibility by setting accurate expectations and making navigation more intuitive, especially for assistive technologies and screen readers.")

                        ComponentPreview(
                            navigation = AnchorTextNavigation
                        ) {
                            AnchorText(anchorTextData)
                        }
                    }
                )
            }
        }
    }
}

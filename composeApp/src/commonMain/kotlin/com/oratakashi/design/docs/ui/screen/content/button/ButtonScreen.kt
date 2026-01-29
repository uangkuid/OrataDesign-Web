package com.oratakashi.design.docs.ui.screen.content.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.navigation.page.ButtonNavigation
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.docs.ui.templates.anchortext.AnchorText
import com.oratakashi.design.docs.ui.templates.button.Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = ButtonNavigation
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
                        Text("Buttons are interactive elements used to initiate actions or direct users to internal or external links. They serve as clear call-to-action components within the interface.")

                        Text("Buttons typically contain concise and descriptive labels, and may optionally include icons to reinforce meaning and improve visual clarity. When designed and used consistently, buttons help guide user behavior and make interactions more intuitive and efficient.")

                        ComponentPreview(
                            navigation = ButtonNavigation
                        ) {
                            Button()
                        }
                    }
                )
            }
        }
    }
}

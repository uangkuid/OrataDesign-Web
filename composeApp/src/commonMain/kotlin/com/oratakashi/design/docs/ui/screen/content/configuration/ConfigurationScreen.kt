package com.oratakashi.design.docs.ui.screen.content.configuration

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.oratakashi.design.docs.navigation.page.ConfigurationNavigation
import com.oratakashi.design.docs.ui.screen.content.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    DetailContent(
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = ConfigurationNavigation
    ) {

    }
}
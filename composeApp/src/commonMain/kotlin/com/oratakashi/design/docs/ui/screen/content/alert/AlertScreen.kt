package com.oratakashi.design.docs.ui.screen.content.alert

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.ui.screen.content.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    DetailContent(
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = AlertNavigation
    ) {

    }
}


package com.oratakashi.design.docs.ui.screen.content.button

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.oratakashi.design.docs.navigation.page.ButtonNavigation
import com.oratakashi.design.docs.ui.screen.content.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    DetailContent(
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = ButtonNavigation
    ) {

    }
}


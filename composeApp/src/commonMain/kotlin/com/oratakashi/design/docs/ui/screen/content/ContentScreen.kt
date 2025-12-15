package com.oratakashi.design.docs.ui.screen.content

import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()
    val coroutineScope = rememberCoroutineScope()
}
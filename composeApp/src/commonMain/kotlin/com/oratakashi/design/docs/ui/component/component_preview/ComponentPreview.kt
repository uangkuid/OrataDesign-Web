package com.oratakashi.design.docs.ui.component.component_preview

import androidx.compose.runtime.Composable

@Composable
fun ComponentPreview(
    content: @Composable () -> Unit
) {
    content.invoke()
}
package com.oratakashi.design.docs.ui.component.color_schema.content

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColorSchemaFooter(
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Diagram of all Orata Design color roles, including optional add-on roles for surface colors and fixed accent colors",
        )
    }
}
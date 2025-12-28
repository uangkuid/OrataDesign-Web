package com.oratakashi.design.docs.ui.component.color_schema.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaItem
import com.oratakashi.design.foundation.OrataTheme

/**
 * Helper composable to render inverse colors section.
 * This includes inverse surface, inverse on-surface, inverse primary, and scrim colors.
 */
@Composable
fun ColumnScope.InverseColorsSection() {
    ColorSchemaItem(
        text = "Inverse Surface",
        backgroundColor = OrataTheme.colors.inverseSurface,
        contentColor = OrataTheme.colors.inverseOnSurface,
        modifier = Modifier.weight(1f)
    )
    ColorSchemaItem(
        text = "Inverse On Surface",
        backgroundColor = OrataTheme.colors.inverseOnSurface,
        contentColor = OrataTheme.colors.inverseSurface,
        modifier = Modifier.weight(1f)
    )
    ColorSchemaItem(
        text = "Inverse Primary",
        backgroundColor = OrataTheme.colors.inversePrimary,
        contentColor = OrataTheme.colors.onPrimaryContainer,
        modifier = Modifier.weight(1f)
    )
    ColorSchemaItem(
        text = "Scrim",
        backgroundColor = OrataTheme.colors.scrim,
        contentColor = Color.White,
        modifier = Modifier.weight(1f).padding(top = 16.dp)
    )
}


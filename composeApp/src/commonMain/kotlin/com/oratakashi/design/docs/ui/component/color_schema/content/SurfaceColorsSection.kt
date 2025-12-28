package com.oratakashi.design.docs.ui.component.color_schema.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaItem
import com.oratakashi.design.foundation.OrataTheme

/**
 * Helper composable to render all surface-related colors.
 * This includes surface variants, container variants, outline colors, etc.
 */
@Composable
fun ColumnScope.SurfaceColorsSection() {
    Row(modifier = Modifier.weight(2f)) {
        ColorSchemaItem(
            text = "Surface Dim ",
            backgroundColor = OrataTheme.colors.surfaceDim,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Surface",
            backgroundColor = OrataTheme.colors.surface,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Surface Bright",
            backgroundColor = OrataTheme.colors.surfaceBright,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
    }

    Row(modifier = Modifier.weight(2f).padding(top = 4.dp)) {
        ColorSchemaItem(
            text = "Surface Container Lowest",
            backgroundColor = OrataTheme.colors.surfaceContainerLowest,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Surface Container Low",
            backgroundColor = OrataTheme.colors.surfaceContainerLow,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Surface Container",
            backgroundColor = OrataTheme.colors.surfaceContainer,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Surface Container High",
            backgroundColor = OrataTheme.colors.surfaceContainerHigh,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Surface Container Highest",
            backgroundColor = OrataTheme.colors.surfaceContainerHighest,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
    }

    Row(modifier = Modifier.weight(1f).padding(top = 4.dp)) {
        ColorSchemaItem(
            text = "On Surface",
            backgroundColor = OrataTheme.colors.onSurface,
            contentColor = OrataTheme.colors.surface,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "On Surface Variant",
            backgroundColor = OrataTheme.colors.onSurfaceVariant,
            contentColor = OrataTheme.colors.surfaceVariant,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Outline",
            backgroundColor = OrataTheme.colors.outline,
            contentColor = OrataTheme.colors.surfaceContainerHighest,
            modifier = Modifier.weight(1f)
        )
        ColorSchemaItem(
            text = "Outline Variant",
            backgroundColor = OrataTheme.colors.outlineVariant,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}


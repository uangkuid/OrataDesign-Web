package com.oratakashi.design.docs.ui.component.color_roles.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaItem
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun SurfaceDimColorRoleContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
    ) {
        ColorSchemaItem(
            text = "Surface Container Lowest",
            backgroundColor = OrataTheme.colors.surfaceContainerLowest,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        )
        ColorSchemaItem(
            text = "Surface Container Low",
            backgroundColor = OrataTheme.colors.surfaceContainerLow,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        )
        ColorSchemaItem(
            text = "Surface Container",
            backgroundColor = OrataTheme.colors.surfaceContainer,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
                .height(120.dp)
        )
        ColorSchemaItem(
            text = "Surface Container High",
            backgroundColor = OrataTheme.colors.surfaceContainerHigh,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        )
        ColorSchemaItem(
            text = "Surface Container Highest",
            backgroundColor = OrataTheme.colors.surfaceContainerHighest,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        )
    }
}


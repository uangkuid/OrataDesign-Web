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
fun OutlineColorRoleContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
    ) {
        ColorSchemaItem(
            text = "Outline",
            backgroundColor = OrataTheme.colors.outline,
            contentColor = OrataTheme.colors.surfaceContainerHighest,
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        )
        ColorSchemaItem(
            text = "Outline Variant",
            backgroundColor = OrataTheme.colors.outlineVariant,
            contentColor = OrataTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        )
    }
}


package com.oratakashi.design.docs.ui.component.color_roles.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaItem
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun PrimaryColorRoleContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(3f)
        ) {
            ColorSchemaItem(
                text = "Primary",
                backgroundColor = OrataTheme.colors.primary,
                contentColor = OrataTheme.colors.onPrimary,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "Primary Container",
                backgroundColor = OrataTheme.colors.primaryContainer,
                contentColor = OrataTheme.colors.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            ColorSchemaItem(
                text = "On Primary",
                backgroundColor = OrataTheme.colors.onPrimary,
                contentColor = OrataTheme.colors.primary,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "On Primary Container",
                backgroundColor = OrataTheme.colors.onPrimaryContainer,
                contentColor = OrataTheme.colors.primaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
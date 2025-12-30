package com.oratakashi.design.docs.ui.component.color_roles.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaItem
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun TertiaryColorRoleContent() {
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
                text = "Tertiary",
                backgroundColor = OrataTheme.colors.tertiary,
                contentColor = OrataTheme.colors.onTertiary,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "Tertiary Container",
                backgroundColor = OrataTheme.colors.tertiaryContainer,
                contentColor = OrataTheme.colors.onTertiaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            ColorSchemaItem(
                text = "On Tertiary",
                backgroundColor = OrataTheme.colors.onTertiary,
                contentColor = OrataTheme.colors.tertiary,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "On Tertiary Container",
                backgroundColor = OrataTheme.colors.onTertiaryContainer,
                contentColor = OrataTheme.colors.tertiaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


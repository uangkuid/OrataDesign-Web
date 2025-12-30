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
fun SecondaryColorRoleContent() {
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
                text = "Secondary",
                backgroundColor = OrataTheme.colors.secondary,
                contentColor = OrataTheme.colors.onSecondary,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "Secondary Container",
                backgroundColor = OrataTheme.colors.secondaryContainer,
                contentColor = OrataTheme.colors.onSecondaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            ColorSchemaItem(
                text = "On Secondary",
                backgroundColor = OrataTheme.colors.onSecondary,
                contentColor = OrataTheme.colors.secondary,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "On Secondary Container",
                backgroundColor = OrataTheme.colors.onSecondaryContainer,
                contentColor = OrataTheme.colors.secondaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


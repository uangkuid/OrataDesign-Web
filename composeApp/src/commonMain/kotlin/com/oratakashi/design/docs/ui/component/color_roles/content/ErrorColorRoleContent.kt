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
fun ErrorColorRoleContent() {
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
                text = "Error",
                backgroundColor = OrataTheme.colors.error,
                contentColor = OrataTheme.colors.onError,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "Error Container",
                backgroundColor = OrataTheme.colors.errorContainer,
                contentColor = OrataTheme.colors.onErrorContainer,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            ColorSchemaItem(
                text = "On Error",
                backgroundColor = OrataTheme.colors.onError,
                contentColor = OrataTheme.colors.error,
                modifier = Modifier.weight(1f)
            )

            ColorSchemaItem(
                text = "On Error Container",
                backgroundColor = OrataTheme.colors.onErrorContainer,
                contentColor = OrataTheme.colors.errorContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

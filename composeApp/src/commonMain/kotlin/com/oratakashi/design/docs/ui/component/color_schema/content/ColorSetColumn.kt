package com.oratakashi.design.docs.ui.component.color_schema.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSchemaItem
import com.oratakashi.design.docs.ui.component.color_schema.ColorSet

/**
 * Helper composable to render a complete color set (main, onMain, container, onContainer).
 * This renders four ColorSchemaItem components stacked vertically.
 */
@Composable
fun ColumnScope.ColorSetColumn(
    colorSet: ColorSet
) {
    ColorSchemaItem(
        text = colorSet.name,
        backgroundColor = colorSet.main,
        contentColor = colorSet.onMain,
        modifier = Modifier.weight(1f)
    )
    ColorSchemaItem(
        text = "On ${colorSet.name}",
        backgroundColor = colorSet.onMain,
        contentColor = colorSet.main,
        modifier = Modifier.weight(1f)
    )
    ColorSchemaItem(
        text = "${colorSet.name} Container",
        backgroundColor = colorSet.container,
        contentColor = colorSet.onContainer,
        modifier = Modifier.weight(1f).padding(top = 4.dp)
    )
    ColorSchemaItem(
        text = "On ${colorSet.name} Container",
        backgroundColor = colorSet.onContainer,
        contentColor = colorSet.container,
        modifier = Modifier.weight(1f)
    )
}


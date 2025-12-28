package com.oratakashi.design.docs.ui.component.color_schema.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.color_schema.ColorSet

/**
 * Helper composable for rendering a color set section in narrow screen layout.
 * Wraps ColorSetColumn with appropriate sizing and padding.
 */
@Composable
fun ColorSetSection(
    colorSet: ColorSet,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 250.dp)
    ) {
        ColorSetColumn(colorSet)
    }
}


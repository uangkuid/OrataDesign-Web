package com.oratakashi.design.docs.ui.component.color_schema

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun ColorSchemaItem(
    text: String = "Surface",
    backgroundColor: Color = OrataTheme.colors.surface,
    contentColor: Color = OrataTheme.colors.onSurface,
    modifier: Modifier = Modifier
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = text,
            style = OrataTheme.typography.bodyMedium(),
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        )
    }
}


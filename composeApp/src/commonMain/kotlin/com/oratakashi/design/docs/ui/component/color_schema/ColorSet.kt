package com.oratakashi.design.docs.ui.component.color_schema

import androidx.compose.ui.graphics.Color

/**
 * Data class to hold color set information for a theme color.
 * Represents a complete color set including main color, its on-color variant,
 * container color, and on-container variant.
 */
data class ColorSet(
    val name: String,
    val main: Color,
    val onMain: Color,
    val container: Color,
    val onContainer: Color
)


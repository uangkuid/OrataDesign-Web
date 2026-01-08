package com.oratakashi.design.docs.ui.component.attribute_table

import androidx.compose.runtime.Composable

/**
 * AttributeData is a data class that represents the structure of an attribute for use in attribute tables within the UI layer.
 *
 * @property name The name of the attribute.
 * @property description A detailed description of the attribute's purpose or usage.
 * @property required Indicates whether the attribute is mandatory.
 * @property defaultValue The default value of the attribute, if any. Null if not specified.
 * @property control A composable lambda that provides the UI control for editing or displaying the attribute.
 *
 * This class is designed for use in Compose Multiplatform projects to ensure clear, maintainable, and type-safe attribute definitions.
 *
 * @author oratakashi
 * @since 06 Jan 2026
 */
data class AttributeData(
    val name: String,
    val description: String,
    val required: Boolean,
    val defaultValue: String? = null,
    val control: @Composable () -> Unit
)

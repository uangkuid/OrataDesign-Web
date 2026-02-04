package com.oratakashi.design.docs.ui.templates.textfield

import com.oratakashi.design.component.textfield.OraTextFieldState

/**
 * Configuration data class for TextField template component.
 * @author oratakashi
 * @since 04 Feb 2026
 * @param value The current text value of the text field
 * @param placeholder The placeholder text to display when the field is empty
 * @param label The label text for the text field
 * @param state The current state of the text field (Default, Error, etc.)
 * @param enabled Whether the text field is enabled or disabled
 * @param singleLine Whether the text field should be single line or multiline
 * @param showLeadingIcon Whether to show the leading icon
 * @param showTrailingIcon Whether to show the trailing icon
 */
data class TextFieldConfig(
    val value: String,
    val placeholder: String,
    val label: String,
    val state: OraTextFieldState,
    val enabled: Boolean,
    val singleLine: Boolean,
    val showLeadingIcon: Boolean,
    val showTrailingIcon: Boolean
)

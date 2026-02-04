package com.oratakashi.design.docs.ui.templates.textfield

import com.oratakashi.design.component.textfield.OraTextFieldState

/**
 * Configuration data class for TextField template component.
 * @author oratakashi
 * @since 04 Feb 2026
 * @param value The current text value of the text field
 * @param placeholder The placeholder text to display when the field is empty
 * @param label The label text for the text field
 * @param state The current state of the text field (Default, Error, Success, Locked)
 * @param enabled Whether the text field is enabled or disabled
 * @param singleLine Whether the text field should be single line or multiline
 * @param showIconLeft Whether to show the left icon
 * @param showIconRight Whether to show the right icon
 */
data class TextFieldConfig(
    val value: String,
    val placeholder: String,
    val label: String,
    val state: OraTextFieldState,
    val enabled: Boolean,
    val singleLine: Boolean,
    val showIconLeft: Boolean,
    val showIconRight: Boolean
)

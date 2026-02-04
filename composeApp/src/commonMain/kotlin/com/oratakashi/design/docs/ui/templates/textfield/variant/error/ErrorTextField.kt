package com.oratakashi.design.docs.ui.templates.textfield.variant.error

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertCircle

/**
 * Error state TextField variant demonstrating validation error behavior.
 * @author oratakashi
 * @since 04 Feb 2026
 */
@Composable
fun ErrorTextField() {
    var value by remember { mutableStateOf("") }
    
    OraTextField(
        value = value,
        onValueChange = { value = it },
        state = OraTextFieldState.Error("This field is required"),
        placeholder = "Enter your password",
        label = "Password",
        iconLeft = {
            Icon(FeatherIcons.AlertCircle, contentDescription = null)
        }
    )
}

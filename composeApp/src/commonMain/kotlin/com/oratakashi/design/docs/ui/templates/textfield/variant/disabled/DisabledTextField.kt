package com.oratakashi.design.docs.ui.templates.textfield.variant.disabled

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import compose.icons.FeatherIcons
import compose.icons.feathericons.User

/**
 * Disabled state TextField variant demonstrating non-interactive text field.
 * @author oratakashi
 * @since 04 Feb 2026
 */
@Composable
fun DisabledTextField() {
    var value by remember { mutableStateOf("Disabled Input") }
    
    OraTextField(
        value = value,
        onValueChange = { value = it },
        state = OraTextFieldState.Default(),
        enabled = false,
        placeholder = "This field is disabled",
        label = "Username",
        leadingIcon = {
            Icon(FeatherIcons.User, contentDescription = null)
        }
    )
}

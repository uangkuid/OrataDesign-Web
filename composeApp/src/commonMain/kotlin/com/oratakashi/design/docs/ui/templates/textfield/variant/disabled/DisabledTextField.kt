package com.oratakashi.design.docs.ui.templates.textfield.variant.disabled

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
    val value = "Disabled Input"
    
    OraTextField(
        value = value,
        onValueChange = { },
        state = OraTextFieldState.Default(),
        enabled = false,
        placeholder = "This field is disabled",
        label = "Username",
        leadingIcon = {
            Icon(FeatherIcons.User, contentDescription = null)
        }
    )
}

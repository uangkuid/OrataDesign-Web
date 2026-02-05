package com.oratakashi.design.docs.ui.templates.textfield.variant.default

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import compose.icons.FeatherIcons
import compose.icons.feathericons.Mail

/**
 * Default state TextField variant demonstrating normal text field behavior.
 * @author oratakashi
 * @since 04 Feb 2026
 */
@Composable
fun DefaultTextField() {
    var value by remember { mutableStateOf("") }
    
    OraTextField(
        value = value,
        onValueChange = { value = it },
        state = OraTextFieldState.Default(),
        placeholder = "Enter your email",
        label = "Email Address",
        iconLeft = {
            Icon(FeatherIcons.Mail, contentDescription = null)
        }
    )
}

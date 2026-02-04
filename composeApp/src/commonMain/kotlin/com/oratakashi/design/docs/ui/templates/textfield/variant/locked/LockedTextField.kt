package com.oratakashi.design.docs.ui.templates.textfield.variant.locked

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import compose.icons.FeatherIcons
import compose.icons.feathericons.Lock

/**
 * Locked state TextField variant demonstrating read-only text field with locked content.
 * @author oratakashi
 * @since 04 Feb 2026
 */
@Composable
fun LockedTextField() {
    var value by remember { mutableStateOf("user@example.com") }
    
    OraTextField(
        value = value,
        onValueChange = { value = it },
        state = OraTextFieldState.Default(),
        enabled = false,
        placeholder = "Locked field",
        label = "Account Email (Locked)",
        leadingIcon = {
            Icon(FeatherIcons.Lock, contentDescription = null)
        }
    )
}

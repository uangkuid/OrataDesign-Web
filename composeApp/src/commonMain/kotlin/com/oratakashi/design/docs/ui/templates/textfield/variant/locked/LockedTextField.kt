package com.oratakashi.design.docs.ui.templates.textfield.variant.locked

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
    val value = "user@example.com"
    
    OraTextField(
        value = value,
        onValueChange = { },
        state = OraTextFieldState.Locked(
            caption = "This email is locked to your account",
            lockedActionText = "Change",
            onClickLockedAction = {
                // Handle unlock action
            }
        ),
        placeholder = "Locked field",
        label = "Account Email (Locked)",
        leadingIcon = {
            Icon(FeatherIcons.Lock, contentDescription = null)
        }
    )
}
}

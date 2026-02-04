package com.oratakashi.design.docs.ui.templates.textfield

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oratakashi.design.component.textfield.OraTextField
import compose.icons.FeatherIcons
import compose.icons.feathericons.Mail
import compose.icons.feathericons.Eye

/**
 * TextField template component that displays an OraTextField with configurable properties.
 * @author oratakashi
 * @since 04 Feb 2026
 * @param config Configuration for the text field appearance and behavior
 * @param modifier Modifier for styling the text field
 * @param onValueChange Callback when the text field value changes
 */
@Composable
fun TextField(
    config: TextFieldConfig,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    OraTextField(
        value = config.value,
        onValueChange = onValueChange,
        state = config.state,
        enabled = config.enabled,
        singleLine = config.singleLine,
        placeholder = config.placeholder,
        label = config.label,
        modifier = modifier,
        leadingIcon = if (config.showLeadingIcon) {
            {
                Icon(FeatherIcons.Mail, contentDescription = null)
            }
        } else null,
        trailingIcon = if (config.showTrailingIcon) {
            {
                Icon(FeatherIcons.Eye, contentDescription = null)
            }
        } else null
    )
}

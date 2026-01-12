package com.oratakashi.design.docs.ui.component.switches

import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import compose.icons.FeatherIcons
import compose.icons.feathericons.Check
import compose.icons.feathericons.X

/**
 * Switch is a composable function that displays a customizable switch component with icon support for checked and unchecked states.
 *
 * @author oratakashi
 * @since 11 Jan 2026
 * @param checked A Boolean value indicating whether the switch is checked.
 * @param onCheckedChange A lambda function invoked when the checked state changes. Pass null to disable interaction.
 * @param modifier A Modifier for styling the switch component.
 */
@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = {
            if (checked) {
                // You can use your own icon here, e.g. Icons.Default.Close
                Icon(
                    imageVector = FeatherIcons.Check,
                    contentDescription = "Close Icon"
                )
            } else {
                Icon(
                    imageVector = FeatherIcons.X,
                    contentDescription = "Check Icon"
                )
            }
        },
        modifier = modifier
    )
}
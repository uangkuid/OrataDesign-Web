package com.oratakashi.design.docs.ui.templates.button

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.button.OraButton
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.ArrowRight

@Composable
fun Button(
    config: ButtonConfig
) {
    OraButton(
        onClick = {

        },
        enabled = config.isEnabled,
        loading = config.isLoading,
        label = config.label,
        size = config.size,
        iconLeft = if (config.showIconLeft) {
            {
                Icon(FeatherIcons.ArrowLeft, null)
            }
        } else null,
        iconRight = if (config.showRightIcon) {
            {
                Icon(FeatherIcons.ArrowRight, null)
            }
        } else null
    )
}
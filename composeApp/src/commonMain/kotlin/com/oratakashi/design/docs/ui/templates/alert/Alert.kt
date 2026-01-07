package com.oratakashi.design.docs.ui.templates.alert

import androidx.compose.runtime.Composable
import com.oratakashi.design.component.alert.OraInfoAlert
import com.oratakashi.design.component.anchortext.OraAnchorText
import com.oratakashi.design.component.anchortext.OraAnchorTextDefaults
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun Alert(
    config: AlertConfig
) {
    OraInfoAlert(
        title = config.title,
        description = config.description,
        visible = config.isVisible,
        showCloseIcon = config.includeOnClose,
        onClose = if (config.includeOnClose) {
            {

            }
        } else {
            null
        },
        action = if (config.includeAction) {
            {
                OraAnchorText(
                    text = "Call to Action",
                    onClick = {
                        // Action here
                    },
                    colors = OraAnchorTextDefaults.colors(
                        contentColor = OrataTheme.colors.info
                    ),
                )
            }
        } else {
            null
        }
    )
}
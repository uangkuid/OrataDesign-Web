package com.oratakashi.design.docs.ui.templates.alert.variant.warning

import androidx.compose.runtime.Composable
import com.oratakashi.design.component.alert.OraWarningAlert
import com.oratakashi.design.component.anchortext.OraAnchorText
import com.oratakashi.design.component.anchortext.OraAnchorTextDefaults
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun WarningAlert() {
    OraWarningAlert(
        title = "Warning Title",
        description = "Interactive monetize corporate alignment",
        action = {
            OraAnchorText(
                text = "Call to Action",
                onClick = {
                    // Action here
                },
                colors = OraAnchorTextDefaults.colors(
                    contentColor = OrataTheme.colors.warning
                ),
            )
        }
    )
}
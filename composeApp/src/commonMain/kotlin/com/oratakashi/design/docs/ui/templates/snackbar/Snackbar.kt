package com.oratakashi.design.docs.ui.templates.snackbar

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.anchortext.OraAnchorText
import com.oratakashi.design.component.anchortext.OraAnchorTextDefaults
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarDefaults
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info

/**
 * Snackbar template component that displays a customizable snackbar.
 * @author oratakashi
 * @since 05 Feb 2026
 * @param config The configuration for the snackbar
 * @param onClose Callback invoked when the close icon is clicked
 */
@Composable
fun Snackbar(
    config: SnackbarConfig,
    onClose: () -> Unit = {}
) {
    OraSnackbar(
        title = {
            Text(config.title)
        },
        description = if (config.description.isNotEmpty()) {
            {
                Text(config.description)
            }
        } else {
            null
        },
        size = config.size,
        showCloseIcon = config.includeOnClose,
        onClose = if (config.includeOnClose) {
            onClose
        } else {
            null
        },
        action = if (config.actionLabel.isNotEmpty()) {
            {
                OraAnchorText(
                    text = config.actionLabel,
                    onClick = {
                        // Action here
                    },
                    colors = OraAnchorTextDefaults.colors(
                        contentColor = OrataTheme.colors.primary
                    ),
                )
            }
        } else {
            null
        },
        icon = if (config.includeIcon) {
            {
                Icon(
                    imageVector = FeatherIcons.Info,
                    contentDescription = null
                )
            }
        } else {
            null
        }
    )
}

package com.oratakashi.design.docs.ui.templates.snackbar.variant.warning

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarTheme
import com.oratakashi.design.component.snackbar.toColor
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertTriangle

/**
 * Warning variant of snackbar component.
 * @author oratakashi
 * @since 05 Feb 2026
 */
@Composable
fun WarningSnackbar() {
    OraSnackbar(
        title = {
            Text("Warning Snackbar")
        },
        description = {
            Text("This is a warning snackbar variant")
        },
        showCloseIcon = true,
        colors = OraSnackbarTheme.Warning.toColor(),
        icon = {
            Icon(
                imageVector = FeatherIcons.AlertTriangle,
                contentDescription = null
            )
        }
    )
}

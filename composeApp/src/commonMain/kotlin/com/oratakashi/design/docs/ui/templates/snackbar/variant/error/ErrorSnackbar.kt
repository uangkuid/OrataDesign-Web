package com.oratakashi.design.docs.ui.templates.snackbar.variant.error

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarTheme
import com.oratakashi.design.component.snackbar.toColor
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertCircle

/**
 * Error variant of snackbar component.
 * @author oratakashi
 * @since 05 Feb 2026
 */
@Composable
fun ErrorSnackbar() {
    OraSnackbar(
        title = {
            Text("Error Snackbar")
        },
        description = {
            Text("This is an error snackbar variant")
        },
        showCloseIcon = true,
        colors = OraSnackbarTheme.Error.toColor(),
        icon = {
            Icon(
                imageVector = FeatherIcons.AlertCircle,
                contentDescription = null
            )
        }
    )
}

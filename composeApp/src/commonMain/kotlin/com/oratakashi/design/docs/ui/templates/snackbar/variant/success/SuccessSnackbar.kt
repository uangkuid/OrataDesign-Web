package com.oratakashi.design.docs.ui.templates.snackbar.variant.success

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarTheme
import com.oratakashi.design.component.snackbar.toColor
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckCircle

/**
 * Success variant of snackbar component.
 * @author oratakashi
 * @since 05 Feb 2026
 */
@Composable
fun SuccessSnackbar() {
    OraSnackbar(
        title = {
            Text("Success Snackbar")
        },
        description = {
            Text("This is a success snackbar variant")
        },
        showCloseIcon = true,
        colors = OraSnackbarTheme.Success.toColor(),
        icon = {
            Icon(
                imageVector = FeatherIcons.CheckCircle,
                contentDescription = null
            )
        }
    )
}

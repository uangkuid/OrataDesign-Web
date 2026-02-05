package com.oratakashi.design.docs.ui.templates.snackbar.variant.normal

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarTheme
import com.oratakashi.design.component.snackbar.toColor
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info

/**
 * Normal variant of snackbar component.
 * @author oratakashi
 * @since 05 Feb 2026
 */
@Composable
fun NormalSnackbar() {
    OraSnackbar(
        title = {
            Text("Normal Snackbar")
        },
        description = {
            Text("This is a normal snackbar variant")
        },
        showCloseIcon = true,
        colors = OraSnackbarTheme.Default.toColor(),
        icon = {
            Icon(
                imageVector = FeatherIcons.Info,
                contentDescription = null
            )
        }
    )
}

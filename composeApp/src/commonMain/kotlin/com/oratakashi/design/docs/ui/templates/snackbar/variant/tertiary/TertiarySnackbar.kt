package com.oratakashi.design.docs.ui.templates.snackbar.variant.tertiary

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarTheme
import com.oratakashi.design.component.snackbar.toColor
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info

/**
 * Tertiary variant of snackbar component.
 * @author oratakashi
 * @since 05 Feb 2026
 */
@Composable
fun TertiarySnackbar() {
    OraSnackbar(
        title = {
            Text("Tertiary Snackbar")
        },
        description = {
            Text("This is a tertiary snackbar variant")
        },
        showCloseIcon = true,
        colors = OraSnackbarTheme.Tertiary.toColor(),
        icon = {
            Icon(
                imageVector = FeatherIcons.Info,
                contentDescription = null
            )
        }
    )
}

package com.oratakashi.design.docs.ui.templates.snackbar

import com.oratakashi.design.component.snackbar.OraSnackbarSize

/**
 * Configuration data class for Snackbar template component.
 * @author oratakashi
 * @since 05 Feb 2026
 * @param title The title text of the snackbar
 * @param description The optional description text of the snackbar
 * @param actionLabel The optional action label text
 * @param includeOnClose Whether to show the close icon
 * @param includeIcon Whether to show the icon
 * @param size The size configuration for this snackbar
 */
data class SnackbarConfig(
    val title: String,
    val description: String = "",
    val actionLabel: String = "",
    val includeOnClose: Boolean = false,
    val includeIcon: Boolean = true,
    val size: OraSnackbarSize = OraSnackbarSize.Large
)

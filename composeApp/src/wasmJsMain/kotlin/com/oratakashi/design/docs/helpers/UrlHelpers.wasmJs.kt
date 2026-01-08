package com.oratakashi.design.docs.helpers

import kotlinx.browser.window

/**
 * WasmJs implementation for opening URLs in external browser (new tab)
 * Uses window.open() with '_blank' target to open URLs in a new browser tab
 * This is appropriate for web platform since the app is already running in a browser
 * @author oratakashi
 * @since 05 Jan 2026
 */
actual object UrlHelpers {
    /**
     * Opens the given URL in a new browser tab using window.open()
     * @param url The URL string to be opened in a new tab
     */
    actual fun openUrl(url: String) {
        try {
            // Open URL in a new tab (_blank) with noopener and noreferrer for security
            window.open(url, "_blank", "noopener,noreferrer")
        } catch (e: Throwable) {
            println("UrlHelpers: Failed to open URL - ${e.message}")
        }
    }
}

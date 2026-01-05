package com.oratakashi.design.docs.helpers

import java.awt.Desktop
import java.net.URI

/**
 * JVM (Desktop) implementation for opening URLs in external browser
 * Uses Java AWT Desktop.browse() to open URLs in the default system browser
 * @author oratakashi
 * @since 05 Jan 2026
 */
actual object UrlHelpers {
    /**
     * Opens the given URL in an external browser using Desktop.browse()
     * @param url The URL string to be opened in the external browser
     */
    actual fun openUrl(url: String) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(URI.create(url))
            } else {
                println("UrlHelpers: Desktop browsing is not supported on this platform")
            }
        } catch (e: Exception) {
            println("UrlHelpers: Failed to open URL - ${e.message}")
        }
    }
}

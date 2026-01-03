package com.oratakashi.design.docs.helpers

/**
 * ClipboardHelpers for clipboard operations across all platforms
 * @author oratakashi
 * @since 03 Jan 2026
 */
expect object ClipboardHelpers {
    /**
     * Copies the given text to the system clipboard
     * @param text The string to be copied to the clipboard
     */
    fun copyToClipboard(text: String)
}

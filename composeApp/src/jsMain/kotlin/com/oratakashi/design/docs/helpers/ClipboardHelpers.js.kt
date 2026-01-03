package com.oratakashi.design.docs.helpers

import kotlinx.browser.window

/**
 * JS implementation for copying text to clipboard
 * Uses the Web Clipboard API available in browsers
 * @author oratakashi
 * @since 03 Jan 2026
 */

/**
 * Copies the given text to the system clipboard
 * @param text The string to be copied to the clipboard
 */
actual fun copyToClipboard(text: String) {
    try {
        // Use the Clipboard API if available
        if (js("typeof navigator !== 'undefined' && navigator.clipboard") as Boolean) {
            // Using writeText method from Clipboard API
            window.navigator.asDynamic().clipboard.writeText(text)
        } else {
            // Fallback: Create a temporary textarea element
            val textarea = kotlinx.browser.document.createElement("textarea")
            textarea.asDynamic().value = text
            textarea.asDynamic().style.position = "fixed"
            textarea.asDynamic().style.opacity = "0"
            kotlinx.browser.document.body?.appendChild(textarea)
            textarea.asDynamic().select()
            kotlinx.browser.document.asDynamic().execCommand("copy")
            kotlinx.browser.document.body?.removeChild(textarea)
        }
    } catch (e: Throwable) {
        println("ClipboardHelpers: Failed to copy to clipboard - ${e.message}")
    }
}

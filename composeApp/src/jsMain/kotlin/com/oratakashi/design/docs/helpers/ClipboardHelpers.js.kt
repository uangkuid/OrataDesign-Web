package com.oratakashi.design.docs.helpers

import kotlinx.browser.window

/**
 * JS implementation for copying text to clipboard
 * Uses the Web Clipboard API available in browsers
 * @author oratakashi
 * @since 03 Jan 2026
 */
actual object ClipboardHelpers {
    /**
     * Copies the given text to the system clipboard
     * @param text The string to be copied to the clipboard
     */
    actual fun copyToClipboard(text: String) {
        try {
            // Use the Clipboard API if available
            if (js("typeof navigator !== 'undefined' && navigator.clipboard") as Boolean) {
                // Using writeText method from Clipboard API (returns a Promise)
                window.navigator.asDynamic().clipboard.writeText(text)
                    .then { 
                        console.log("Text copied to clipboard successfully")
                    }
                    .catch { error: dynamic ->
                        console.error("Failed to copy text to clipboard: ", error)
                    }
            } else {
                // Fallback: Create a temporary textarea element
                // Note: execCommand is deprecated but used as fallback for older browsers
                console.warn("Using deprecated execCommand as fallback for clipboard operation")
                val textarea = kotlinx.browser.document.createElement("textarea")
                textarea.asDynamic().value = text
                textarea.asDynamic().style.position = "fixed"
                textarea.asDynamic().style.opacity = "0"
                kotlinx.browser.document.body?.appendChild(textarea)
                textarea.asDynamic().select()
                val success = kotlinx.browser.document.asDynamic().execCommand("copy") as Boolean
                kotlinx.browser.document.body?.removeChild(textarea)
                if (success) {
                    console.log("Text copied to clipboard using fallback method")
                } else {
                    console.error("Failed to copy text to clipboard using fallback method")
                }
            }
        } catch (e: Throwable) {
            println("ClipboardHelpers: Failed to copy to clipboard - ${e.message}")
        }
    }
}

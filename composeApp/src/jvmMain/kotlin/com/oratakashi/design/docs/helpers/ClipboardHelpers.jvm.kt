package com.oratakashi.design.docs.helpers

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

/**
 * JVM (Desktop) implementation for copying text to clipboard
 * Uses Java AWT Toolkit's clipboard functionality
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
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            val stringSelection = StringSelection(text)
            clipboard.setContents(stringSelection, null)
        } catch (e: Exception) {
            println("ClipboardHelpers: Failed to copy to clipboard - ${e.message}")
        }
    }
}

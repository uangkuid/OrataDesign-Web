package com.oratakashi.design.docs.helpers

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLTextAreaElement

/**
 * WasmJs implementation for copying text to clipboard
 * Uses the Web Clipboard API available in modern browsers
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
            // Use the modern Clipboard API if available
            val navigator = window.navigator
            if (hasClipboardAPI()) {
                writeToClipboard(text)
            } else {
                // Fallback: Create a temporary textarea element
                // Note: execCommand is deprecated but used as fallback for older browsers
                copyUsingExecCommand(text)
            }
        } catch (e: Throwable) {
            println("ClipboardHelpers: Failed to copy to clipboard - ${e.message}")
        }
    }
    
    /**
     * Check if Clipboard API is available
     */
    private fun hasClipboardAPI(): Boolean {
        return js("typeof navigator !== 'undefined' && navigator.clipboard && typeof navigator.clipboard.writeText === 'function'")
    }
    
    /**
     * Write text to clipboard using Clipboard API
     */
    private fun writeToClipboard(text: String) {
        js("""
            navigator.clipboard.writeText(text)
                .then(function() {
                    console.log('Text copied to clipboard successfully');
                })
                .catch(function(error) {
                    console.error('Failed to copy text to clipboard: ', error);
                });
        """)
    }
    
    /**
     * Fallback method using deprecated execCommand
     */
    private fun copyUsingExecCommand(text: String) {
        val textarea = document.createElement("textarea") as HTMLTextAreaElement
        textarea.value = text
        textarea.style.position = "fixed"
        textarea.style.opacity = "0"
        document.body?.appendChild(textarea)
        textarea.select()
        
        val success = js("document.execCommand('copy')") as Boolean
        
        document.body?.removeChild(textarea)
        
        if (success) {
            println("Text copied to clipboard using fallback method")
        } else {
            println("Failed to copy text to clipboard using fallback method")
        }
    }
}

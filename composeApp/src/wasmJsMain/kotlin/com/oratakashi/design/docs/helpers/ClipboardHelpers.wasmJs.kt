package com.oratakashi.design.docs.helpers

import kotlinx.browser.document
import org.w3c.dom.HTMLTextAreaElement
import kotlin.js.JsAny
import kotlin.js.Promise

/**
 * External declaration for Navigator Clipboard API
 * @author oratakashi
 * @since 04 Jan 2026
 */
@OptIn(ExperimentalWasmJsInterop::class)
external interface Clipboard {
    fun writeText(text: String): Promise<JsAny?>
}

/**
 * External declaration for Navigator with clipboard property
 * @author oratakashi
 * @since 04 Jan 2026
 */
@OptIn(ExperimentalWasmJsInterop::class)
external interface NavigatorClipboard {
    val clipboard: Clipboard?
}

/**
 * External declaration to access navigator from JavaScript
 * @author oratakashi
 * @since 04 Jan 2026
 */
@OptIn(ExperimentalWasmJsInterop::class)
external val navigator: NavigatorClipboard

/**
 * External declaration for console API
 * @author oratakashi
 * @since 04 Jan 2026
 */
@OptIn(ExperimentalWasmJsInterop::class)
external object console {
    fun log(message: String)
}

/**
 * WasmJs implementation for copying text to clipboard
 * Uses the Web Clipboard API available in modern browsers
 * @author oratakashi
 * @since 03 Jan 2026
 */
@OptIn(ExperimentalWasmJsInterop::class)
actual object ClipboardHelpers {
    /**
     * Copies the given text to the system clipboard
     * @param text The string to be copied to the clipboard
     */
    actual fun copyToClipboard(text: String) {
        try {
            // Try to use modern Clipboard API
            val clipboard = navigator.clipboard
            if (clipboard != null) {
                clipboard.writeText(text)
                console.log("Text copied to clipboard successfully")
            } else {
                // Fallback: Create a temporary textarea element
                // Note: execCommand is deprecated but used as fallback for older browsers
                copyUsingExecCommand(text)
            }
        } catch (e: Throwable) {
            // If Clipboard API fails, try fallback method
            println("ClipboardHelpers: Failed to copy using Clipboard API, trying fallback - ${e.message}")
            try {
                copyUsingExecCommand(text)
            } catch (fallbackError: Throwable) {
                println("ClipboardHelpers: Failed to copy to clipboard - ${fallbackError.message}")
            }
        }
    }

    /**
     * Fallback method using deprecated execCommand
     * @param text The string to be copied to the clipboard
     */
    private fun copyUsingExecCommand(text: String) {
        val textarea = document.createElement("textarea") as HTMLTextAreaElement
        textarea.value = text
        textarea.style.position = "fixed"
        textarea.style.opacity = "0"
        document.body?.appendChild(textarea)
        textarea.select()
        
        try {
            document.execCommand("copy")
            println("Text copied to clipboard using fallback method")
        } catch (e: Throwable) {
            println("Failed to copy text to clipboard using fallback method: ${e.message}")
        } finally {
            document.body?.removeChild(textarea)
        }
    }
}

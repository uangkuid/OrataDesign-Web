package com.oratakashi.design.docs.helpers

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Android implementation for copying text to clipboard
 * Uses Android's ClipboardManager service
 * @author oratakashi
 * @since 03 Jan 2026
 */
actual object ClipboardHelpers : KoinComponent {
    
    private val applicationContext: Context? by inject()

    /**
     * Copies the given text to the system clipboard
     * @param text The string to be copied to the clipboard
     */
    actual fun copyToClipboard(text: String) {
        val context = applicationContext ?: run {
            println("ClipboardHelpers: Context not initialized. Call initClipboard() first.")
            return
        }
        
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        if (clipboardManager != null) {
            val clipData = ClipData.newPlainText("text", text)
            clipboardManager.setPrimaryClip(clipData)
        } else {
            println("ClipboardHelpers: ClipboardManager not available")
        }
    }
}

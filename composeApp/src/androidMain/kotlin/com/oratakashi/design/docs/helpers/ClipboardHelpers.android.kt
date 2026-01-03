package com.oratakashi.design.docs.helpers

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 * Android implementation for copying text to clipboard
 * Uses Android's ClipboardManager service
 * @author oratakashi
 * @since 03 Jan 2026
 */
actual object ClipboardHelpers {
    
    private var applicationContext: Context? = null

    /**
     * Initialize the clipboard helper with application context
     * Should be called once from the Application or MainActivity
     * @param context Android application context
     */
    fun initClipboard(context: Context) {
        applicationContext = context.applicationContext
    }

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

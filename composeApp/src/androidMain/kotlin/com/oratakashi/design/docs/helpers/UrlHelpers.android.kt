package com.oratakashi.design.docs.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Android implementation for opening URLs in external browser
 * Uses Android's Intent with ACTION_VIEW to launch the default browser
 * @author oratakashi
 * @since 05 Jan 2026
 */
actual object UrlHelpers : KoinComponent {
    
    private val applicationContext: Context? by inject()

    /**
     * Opens the given URL in an external browser using Intent.ACTION_VIEW
     * @param url The URL string to be opened in the external browser
     */
    actual fun openUrl(url: String) {
        val context = applicationContext ?: run {
            println("UrlHelpers: Context not initialized.")
            return
        }
        
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            println("UrlHelpers: Failed to open URL - ${e.message}")
        }
    }
}

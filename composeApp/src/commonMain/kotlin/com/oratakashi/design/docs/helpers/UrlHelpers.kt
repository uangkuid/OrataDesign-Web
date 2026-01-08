package com.oratakashi.design.docs.helpers

/**
 * UrlHelpers for opening URLs in external browser across all platforms
 * @author oratakashi
 * @since 05 Jan 2026
 */
expect object UrlHelpers {
    /**
     * Opens the given URL in an external browser
     * 
     * For web platform, the URL will be opened in a new tab
     * For Android, it will use Intent.ACTION_VIEW
     * For Desktop/JVM, it will use Desktop.browse()
     * 
     * @param url The URL string to be opened in the external browser
     */
    fun openUrl(url: String)
}

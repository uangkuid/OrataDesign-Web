package com.oratakashi.design.docs

/**
 * Platform interface for identifying the current runtime platform
 * @author oratakashi
 * @since 05 Jan 2026
 */
interface Platform {
    /**
     * Name of the current platform
     */
    val name: String
}

/**
 * Expect function to get the current platform instance
 * Each platform (JS, WasmJS, JVM, Android) provides its own implementation
 * @return Platform instance for the current target
 */
expect fun getPlatform(): Platform

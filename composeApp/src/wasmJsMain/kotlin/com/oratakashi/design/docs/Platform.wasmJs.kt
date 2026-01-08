package com.oratakashi.design.docs

/**
 * WasmJs Platform implementation
 * Identifies the platform as Web with Kotlin/Wasm-JS
 * @author oratakashi
 * @since 05 Jan 2026
 */
class WasmJsPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm-JS"
}

/**
 * Returns the WasmJs platform instance
 * @return Platform instance for Wasm-JS target
 */
actual fun getPlatform(): Platform = WasmJsPlatform()

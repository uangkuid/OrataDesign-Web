package com.oratakashi.design.docs.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.xml.xml
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

/**
 * Implementasi HttpClient untuk WASM/Web menggunakan Js engine
 * Engine ini menggunakan Fetch API browser
 */
actual fun createHttpClient(): HttpClient {
    println("HttpClientFactory: Creating WASM/Web HttpClient with Js engine")
    return HttpClient(Js) {
        install(ContentNegotiation) {
            val xmlFormat = XML {
                autoPolymorphic = false
                indentString = "  "
                xmlDeclMode = XmlDeclMode.Auto
                // Penting untuk parsing XML yang fleksibel
                repairNamespaces = true
            }
            xml(contentType = ContentType.Application.Xml, format = xmlFormat)
            xml(contentType = ContentType.Text.Xml, format = xmlFormat)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("Ktor: $message")
                }
            }
            level = LogLevel.ALL
        }
    }
}

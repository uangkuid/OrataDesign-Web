package com.oratakashi.design.docs.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.xml.xml
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

/**
 * Implementasi HttpClient untuk Android menggunakan Android engine
 */
actual fun createHttpClient(): HttpClient {
    println("HttpClientFactory: Creating Android HttpClient with Android engine")
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            xml(
                format = XML {
                    autoPolymorphic = false
                    indentString = "  "
                    xmlDeclMode = XmlDeclMode.Auto
                    // Penting untuk parsing XML yang fleksibel
                    repairNamespaces = true
                    xmlVersion = nl.adaptivity.xmlutil.XmlVersion.XML10
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}


package com.oratakashi.design.docs.data.remote

import io.ktor.client.HttpClient

/**
 * Factory untuk membuat HttpClient yang platform-specific
 * Karena setiap platform memiliki engine yang berbeda
 */
expect fun createHttpClient(): HttpClient


package com.oratakashi.design.docs.data.remote.service

import com.oratakashi.design.docs.data.model.maven.MavenMetadataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface MavenApiService {
    suspend fun getMavenMetadata(): MavenMetadataResponse
}

class MavenApiServiceImpl(
    private val httpClient: HttpClient
) : MavenApiService {

    companion object {
        private const val BASE_URL = "https://repo1.maven.org/maven2/com/oratakashi/design"
    }

    override suspend fun getMavenMetadata(): MavenMetadataResponse {
        println("MavenApiService: Making HTTP request to $BASE_URL/maven-metadata.xml")
        return try {
            val response = httpClient.get("$BASE_URL/maven-metadata.xml")
            println("MavenApiService: HTTP request successful, status: ${response.status}")
            val body = response.body<MavenMetadataResponse>()
            println("MavenApiService: Response body parsed successfully")
            body
        } catch (e: Exception) {
            println("MavenApiService: HTTP request failed - ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}


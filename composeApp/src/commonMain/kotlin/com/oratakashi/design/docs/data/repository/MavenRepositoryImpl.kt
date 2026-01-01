package com.oratakashi.design.docs.data.repository

import com.oratakashi.design.docs.data.model.maven.MavenMetadataResponse
import com.oratakashi.design.docs.data.remote.service.MavenApiService
import com.oratakashi.design.docs.domain.model.MavenMetadata
import com.oratakashi.design.docs.domain.model.MavenVersioning
import com.oratakashi.design.docs.domain.repository.MavenRepository

class MavenRepositoryImpl(
    private val apiService: MavenApiService
) : MavenRepository {

    override suspend fun getMavenMetadata(): Result<MavenMetadata> {
        return try {
            val response = apiService.getMavenMetadata()
            val domainModel = response.toDomainModel()
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun MavenMetadataResponse.toDomainModel(): MavenMetadata {
        return MavenMetadata(
            groupId = this.groupId,
            artifactId = this.artifactId,
            versioning = MavenVersioning(
                latest = this.versioning.latest,
                release = this.versioning.release,
                versions = this.versioning.versions.version,
                lastUpdated = this.versioning.lastUpdated
            )
        )
    }
}


package com.oratakashi.design.docs.domain.usecase

import com.oratakashi.design.docs.domain.model.MavenMetadata
import com.oratakashi.design.docs.domain.repository.MavenRepository

class GetMavenMetadataUseCase(
    private val repository: MavenRepository
) {
    suspend operator fun invoke(): Result<MavenMetadata> {
        return repository.getMavenMetadata()
    }
}


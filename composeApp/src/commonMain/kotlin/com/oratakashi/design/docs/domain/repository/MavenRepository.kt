package com.oratakashi.design.docs.domain.repository

import com.oratakashi.design.docs.domain.model.MavenMetadata

interface MavenRepository {
    suspend fun getMavenMetadata(): Result<MavenMetadata>
}


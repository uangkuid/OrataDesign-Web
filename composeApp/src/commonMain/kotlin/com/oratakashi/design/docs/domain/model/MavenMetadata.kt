package com.oratakashi.design.docs.domain.model

data class MavenMetadata(
    val groupId: String,
    val artifactId: String,
    val versioning: MavenVersioning
)

data class MavenVersioning(
    val latest: String,
    val release: String,
    val versions: List<String>,
    val lastUpdated: String
)


package com.oratakashi.design.docs.data.model.maven

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlChildrenName

/**
 * Response model untuk Maven metadata XML
 */
@Serializable
@XmlSerialName("metadata", "", "")
data class MavenMetadataResponse(
    val groupId: String = "",
    val artifactId: String = "",
    val versioning: Versioning = Versioning()
)

@Serializable
@XmlSerialName("versioning", "", "")
data class Versioning(
    val latest: String = "",
    val release: String = "",
    val versions: Versions = Versions(),
    val lastUpdated: String = ""
)

@Serializable
@XmlSerialName("versions", "", "")
data class Versions(
    @XmlChildrenName("version", "", "")
    val version: List<String> = emptyList()
)


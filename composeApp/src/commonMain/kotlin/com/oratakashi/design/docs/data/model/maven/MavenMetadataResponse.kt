package com.oratakashi.design.docs.data.model.maven

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("metadata", "", "")
data class MavenMetadataResponse(
    @XmlSerialName("groupId", "", "")
    val groupId: String = "",
    @XmlSerialName("artifactId", "", "")
    val artifactId: String = "",
    @XmlSerialName("versioning", "", "")
    val versioning: Versioning = Versioning()
)

@Serializable
@XmlSerialName("versioning", "", "")
data class Versioning(
    @XmlSerialName("latest", "", "")
    val latest: String = "",
    @XmlSerialName("release", "", "")
    val release: String = "",
    @XmlSerialName("versions", "", "")
    val versions: Versions = Versions(),
    @XmlSerialName("lastUpdated", "", "")
    val lastUpdated: String = ""
)

@Serializable
@XmlSerialName("versions", "", "")
data class Versions(
    @XmlSerialName("version", "", "")
    val version: List<String> = emptyList()
)


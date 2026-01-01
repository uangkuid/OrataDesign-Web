package com.oratakashi.design.docs.data.model.maven

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("metadata", "", "")
data class MavenMetadataResponse(
    @XmlElement
    val groupId: String = "",
    @XmlElement
    val artifactId: String = "",
    @XmlElement
    @XmlSerialName("versioning", "", "")
    val versioning: Versioning = Versioning()
)

@Serializable
@XmlElement(true)
@XmlSerialName("versioning", "", "")
data class Versioning(
    @XmlElement
    @XmlSerialName("latest")
    val latest: String = "",
    @XmlElement
    @XmlSerialName("release")
    val release: String = "",
    @XmlElement(true)
    @XmlSerialName("versions")
    val versions: Versions = Versions(),
    @XmlElement
    @XmlSerialName("lastUpdated")
    val lastUpdated: String = ""
)

@Serializable
@XmlSerialName("versions", "", "")
data class Versions(
    @XmlElement(true)
    @XmlSerialName("version", "", "")
    val version: List<String> = emptyList()
)

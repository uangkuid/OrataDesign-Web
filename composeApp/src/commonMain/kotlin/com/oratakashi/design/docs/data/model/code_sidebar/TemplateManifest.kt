package com.oratakashi.design.docs.data.model.code_sidebar

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class TemplateManifest(
    @SerialName("name")
	val name: String? = null,

    @SerialName("variant")
    val variant: List<TemplateManifest>? = null,

    @SerialName("content")
	val content: List<TemplateContent>? = null
)

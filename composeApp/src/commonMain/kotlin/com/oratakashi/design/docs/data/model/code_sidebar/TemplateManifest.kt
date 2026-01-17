package com.oratakashi.design.docs.data.model.code_sidebar

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TemplateManifest(
	@SerialName("name")
	val name: String? = null,

	@SerialName("content")
	val content: List<TemplateContent>? = null
)

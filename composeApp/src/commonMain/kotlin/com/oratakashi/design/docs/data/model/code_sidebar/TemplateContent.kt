package com.oratakashi.design.docs.data.model.code_sidebar

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TemplateContent(

	@SerialName("extension")
	val extension: String? = null,

	@SerialName("filepath")
	val filepath: String? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("fileType")
	val fileType: String? = null
)

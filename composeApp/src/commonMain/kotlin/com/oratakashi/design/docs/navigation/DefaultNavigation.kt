package com.oratakashi.design.docs.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
object DefaultNavigation: BaseNavigation {
    override fun getSerializer() = serializer()
}
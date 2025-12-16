package com.oratakashi.design.docs.navigation

import com.oratakashi.design.app.navigation.contract.BaseNavigation
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
object InstallationNavigation: BaseNavigation {
    override fun getSerializer() = serializer()
}
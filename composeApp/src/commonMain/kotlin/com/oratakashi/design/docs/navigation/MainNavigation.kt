package com.oratakashi.design.docs.navigation

import com.oratakashi.design.docs.navigation.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object MainNavigation: BaseNavigation {
    override fun getSerializer() = serializer()
}
package com.oratakashi.design.docs.navigation

import com.oratakashi.design.app.navigation.contract.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object HomeNavigation: BaseNavigation {
    override fun getSerializer() = serializer()
}
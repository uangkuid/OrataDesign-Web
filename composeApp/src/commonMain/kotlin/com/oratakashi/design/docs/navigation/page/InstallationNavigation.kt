package com.oratakashi.design.docs.navigation.page

import com.oratakashi.design.docs.navigation.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object InstallationNavigation : BaseNavigation {
    override fun getSerializer() = serializer()
}
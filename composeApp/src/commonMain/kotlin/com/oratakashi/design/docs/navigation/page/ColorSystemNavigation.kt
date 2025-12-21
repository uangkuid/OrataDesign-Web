package com.oratakashi.design.docs.navigation.page

import com.oratakashi.design.docs.navigation.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object ColorSystemNavigation : BaseNavigation {
    override fun getSerializer() = serializer()
}

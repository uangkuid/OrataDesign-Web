package com.oratakashi.design.docs.navigation.page

import com.oratakashi.design.app.navigation.contract.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object ButtonNavigation : BaseNavigation {
    override fun getSerializer() = serializer()
}


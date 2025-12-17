package com.oratakashi.design.docs.navigation.page

import com.oratakashi.design.app.navigation.contract.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object AnchorTextNavigation : BaseNavigation {
    override fun getSerializer() = serializer()
}


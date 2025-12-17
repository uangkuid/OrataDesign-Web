package com.oratakashi.design.docs.navigation.page

import com.oratakashi.design.app.navigation.contract.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object TextFieldNavigation : BaseNavigation {
    override fun getSerializer() = serializer()
}


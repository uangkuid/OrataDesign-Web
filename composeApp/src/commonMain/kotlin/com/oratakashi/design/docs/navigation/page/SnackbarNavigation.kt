package com.oratakashi.design.docs.navigation.page

import com.oratakashi.design.docs.navigation.BaseNavigation
import kotlinx.serialization.Serializable

@Serializable
object SnackbarNavigation : BaseNavigation {
    override fun getSerializer() = serializer()
}

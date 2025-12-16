package com.oratakashi.design.docs.models.sidebar

import com.oratakashi.design.app.navigation.contract.BaseNavigation

data class SidebarItem(
    val label: String,
    val navigation: BaseNavigation? = null
)
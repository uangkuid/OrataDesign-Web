package com.oratakashi.design.docs.models.sidebar

import com.oratakashi.design.docs.navigation.BaseNavigation

data class SidebarItem(
    val label: String,
    val navigation: BaseNavigation? = null
)
package com.oratakashi.design.docs.data.model.sidebar

import com.oratakashi.design.docs.navigation.BaseNavigation

data class SidebarItem(
    val label: String,
    val navigation: BaseNavigation? = null
)
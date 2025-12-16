package com.oratakashi.design.docs

import com.oratakashi.design.docs.models.sidebar.SidebarItem
import com.oratakashi.design.docs.models.sidebar.SidebarSection
import com.oratakashi.design.docs.navigation.InstallationNavigation

object Constant {
    val defaultSelectionSidebar = SidebarItem(
        label = "Installation",
        navigation = InstallationNavigation
    )
    val sidebarItem = listOf(
        SidebarSection(
            title = "Prologue",
            item = listOf(
                SidebarItem(
                    label = "Installation",
                    navigation = InstallationNavigation
                ),
                SidebarItem(
                    label = "Configuration"
                ),
            )
        ),
        SidebarSection(
            title = "Foundation",
            item = listOf(
                SidebarItem(
                    label = "Color System"
                ),
                SidebarItem(
                    label = "Typography"
                ),
            )
        ),
        SidebarSection(
            title = "Components",
            item = listOf(
                SidebarItem(
                    label = "Alert"
                ),
                SidebarItem(
                    label = "Anchor Text"
                ),
                SidebarItem(
                    label = "Button"
                ),
                SidebarItem(
                    label = "Snackbar"
                ),
                SidebarItem(
                    label = "TextField"
                ),
            )
        )
    )
}
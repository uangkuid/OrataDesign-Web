package com.oratakashi.design.docs

import com.oratakashi.design.docs.data.model.sidebar.SidebarItem
import com.oratakashi.design.docs.data.model.sidebar.SidebarSection
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.navigation.page.ButtonNavigation
import com.oratakashi.design.docs.navigation.page.ColorSystemNavigation
import com.oratakashi.design.docs.navigation.page.ConfigurationNavigation
import com.oratakashi.design.docs.navigation.page.InstallationNavigation
import com.oratakashi.design.docs.navigation.page.SnackbarNavigation
import com.oratakashi.design.docs.navigation.page.TextFieldNavigation
import com.oratakashi.design.docs.navigation.page.TypographyNavigation

object Config {
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
                    label = "Configuration",
                    navigation = ConfigurationNavigation
                ),
            )
        ),
        SidebarSection(
            title = "Foundation",
            item = listOf(
                SidebarItem(
                    label = "Color System",
                    navigation = ColorSystemNavigation
                ),
                SidebarItem(
                    label = "Typography",
                    navigation = TypographyNavigation
                ),
            )
        ),
        SidebarSection(
            title = "Components",
            item = listOf(
                SidebarItem(
                    label = "Alert",
                    navigation = AlertNavigation
                ),
                SidebarItem(
                    label = "Anchor Text",
                    navigation = AnchorTextNavigation
                ),
                SidebarItem(
                    label = "Button",
                    navigation = ButtonNavigation
                ),
                SidebarItem(
                    label = "Snackbar",
                    navigation = SnackbarNavigation
                ),
                SidebarItem(
                    label = "TextField",
                    navigation = TextFieldNavigation
                ),
            )
        )
    )
}
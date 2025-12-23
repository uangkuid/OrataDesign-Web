package com.oratakashi.design.docs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToBrowserNavigation
import com.oratakashi.design.docs.navigation.HomeNavigation
import com.oratakashi.design.docs.ui.App
import kotlinx.browser.document
import kotlinx.browser.window
import com.oratakashi.design.docs.Config

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    val body = document.body ?: return
    ComposeViewport(body) {
        // Build a route -> label map from the sidebar configuration so titles are auto-derived
        val routeToLabel: Map<String, String> = Config.sidebarItem
            .flatMap { it.item }
            .mapNotNull { item ->
                val route = item.navigation?.route
                val label = item.label
                if (route != null) route to label else null
            }
            .toMap()

        App(
            onNavHostReady = {
                it.bindToBrowserNavigation { entry ->
                    val route = entry.destination.route.orEmpty()
                    when {
                        route == HomeNavigation.route -> {
                            window.document.title = "Home - Orata Design System"
                            ""
                        }
                        else -> {
                            val label = routeToLabel[route] ?: "Docs"
                            window.document.title = "$label - Orata Design System"
                            val slug = label.lowercase().replace("\\s+".toRegex(), "")
                            "?page=$slug"
                        }
                    }
                }
            }
        )
    }
}
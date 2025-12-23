package com.oratakashi.design.docs

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToBrowserNavigation
import com.oratakashi.design.docs.navigation.HomeNavigation
import com.oratakashi.design.docs.navigation.MainNavigation
import com.oratakashi.design.docs.ui.App
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        // Build a slug -> route map (slug derived same way as in bindToBrowserNavigation)
        val slugToRoute: Map<String, String> = routeToLabel
            .map { (route, label) ->
                val slug = label.lowercase().replace("\\s+".toRegex(), "")
                slug to route
            }
            .toMap()

        // Read initial slug from URL (e.g., ?page=installation) and navigate directly
        val initSlug = window.location.search
            .substringAfter("?page=", "")
            .substringBefore("&")

        println("initSlug: $initSlug")
        val coroutineScope = rememberCoroutineScope()

        App(
            hasDeeplink = initSlug.isNotEmpty(),
            onNavHostReady = { navController ->
                coroutineScope.launch {
                    if (initSlug.isNotEmpty()) {
                        val targetRoute = slugToRoute[initSlug]
                        println("initSlug: $initSlug - $targetRoute")
                        if (targetRoute != null) {
                            println("Try to naviagate: $initSlug -> $targetRoute")
                            navController.navigate(targetRoute) {
                                launchSingleTop = true
                            }
                        }
                    }
                }

                // Bind browser navigation first, so initial navigate updates title and query param
                navController.bindToBrowserNavigation { entry ->
                    val route = entry.destination.route.orEmpty()
                    when {
                        route == HomeNavigation.route -> {
                            window.document.title = "Home - Orata Design System"
                            ""
                        }
                        route == MainNavigation.route -> {
                            // Ignore MainNavigation: no query param, keep a generic title
                            window.document.title = "Orata Design System"
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
package com.oratakashi.design.docs

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.github.terrakok.navigation3.browser.ChronologicalBrowserNavigation
import com.github.terrakok.navigation3.browser.buildBrowserHistoryFragment
import com.github.terrakok.navigation3.browser.getBrowserHistoryFragmentName
import com.oratakashi.design.docs.di.AppModule
import com.oratakashi.design.docs.navigation.BaseNavigation
import com.oratakashi.design.docs.navigation.MainNavigation
import com.oratakashi.design.docs.ui.App
import kotlinx.browser.document
import kotlinx.browser.window
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    startKoin {
        modules(*AppModule.provideModule())
    }

    val body = document.body ?: return
    ComposeViewport(body) {
        // Build a route -> label map from the sidebar configuration so titles/fragment slugs
        // are auto-derived, same source of truth as before.
        val routeToLabel: Map<String, String> = Config.sidebarItem
            .flatMap { it.item }
            .mapNotNull { item ->
                val route = item.navigation?.route
                val label = item.label
                if (route != null) route to label else null
            }
            .toMap()

        // slug -> route object, so a restored browser history fragment can be turned back into
        // the BaseNavigation instance that needs to be pushed onto the detail back stack.
        val slugToNavigation: Map<String, BaseNavigation> = Config.sidebarItem
            .flatMap { it.item }
            .mapNotNull { item ->
                val navigation = item.navigation ?: return@mapNotNull null
                val slug = item.label.lowercase().replace("\\s+".toRegex(), "")
                slug to navigation
            }
            .toMap()

        var detailBackStack by remember { mutableStateOf<NavBackStack<NavKey>?>(null) }

        App(
            onBackStackReady = { backStack ->
                // A URL fragment (e.g. #installation) means we should land directly on the docs
                // shell instead of the Home splash screen; ChronologicalBrowserNavigation (bound
                // below, once ContentScreen's detail back stack is available) resolves the
                // fragment into the actual detail route via restoreKey.
                if (window.location.hash.length > 1) {
                    backStack.add(MainNavigation)
                }
            },
            onDetailBackStackReady = { stack ->
                detailBackStack = stack
            }
        )

        val stack = detailBackStack
        if (stack != null) {
            LaunchedEffect(stack.lastOrNull()) {
                val nav = stack.lastOrNull() as? BaseNavigation
                document.title = if (nav != null) {
                    "${routeToLabel[nav.route] ?: "Docs"} - Orata Design System"
                } else {
                    "Orata Design System"
                }
            }

            // NOTE: Nav3 has no NavController, so the old `bindToBrowserNavigation` experimental
            // API is replaced by this third-party helper (com.github.terrakok:navigation3-browser).
            // It syncs `stack` with the URL *fragment* (`#slug`), which replaces the old `?page=`
            // query-param scheme - bookmarked/shared `?page=xxx` links from before this migration
            // will no longer resolve to a specific page after this change.
            ChronologicalBrowserNavigation(
                backStack = stack,
                saveKey = saveKey@{ key ->
                    val nav = key as? BaseNavigation ?: return@saveKey null
                    val label = routeToLabel[nav.route] ?: return@saveKey null
                    val slug = label.lowercase().replace("\\s+".toRegex(), "")
                    buildBrowserHistoryFragment(slug)
                },
                restoreKey = { fragment ->
                    slugToNavigation[getBrowserHistoryFragmentName(fragment)]
                }
            )
        }
    }
}

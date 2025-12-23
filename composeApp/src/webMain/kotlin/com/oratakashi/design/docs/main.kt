package com.oratakashi.design.docs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToBrowserNavigation
import com.oratakashi.design.docs.navigation.HomeNavigation
import com.oratakashi.design.docs.ui.App
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    val body = document.body ?: return
    ComposeViewport(body) {
        App(
            onNavHostReady = {
                it.bindToBrowserNavigation { entry ->
                    val route = entry.destination.route.orEmpty()
                    when {
                        route == HomeNavigation.route -> {
                            window.document.title = "Home - Orata Design System"
                            "/"
                        }
                        else -> {
                            window.document.title = "Docs - Orata Design System"
                            "docs"
                        }
                    }
                }
            }
        )
    }
}
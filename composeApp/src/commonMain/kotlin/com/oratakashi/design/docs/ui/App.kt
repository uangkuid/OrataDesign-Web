package com.oratakashi.design.docs.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.oratakashi.design.docs.navigation.HomeNavigation
import com.oratakashi.design.docs.navigation.MainNavigation
import com.oratakashi.design.docs.navigation.navigationConfig
import com.oratakashi.design.docs.ui.screen.content.ContentScreen
import com.oratakashi.design.docs.ui.screen.home.HomeScreen
import com.oratakashi.design.foundation.OrataAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(
    onBackStackReady: (NavBackStack<NavKey>) -> Unit = {},
    onDetailBackStackReady: (NavBackStack<NavKey>) -> Unit = {},
) {
    val backStack = rememberNavBackStack(navigationConfig, HomeNavigation)
    OrataAppTheme(darkTheme = true) {
        LaunchedEffect(Unit) {
            onBackStackReady(backStack)
        }
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            NavDisplay(
                backStack = backStack,
                entryProvider = entryProvider {
                    entry<HomeNavigation> {
                        HomeScreen(
                            modifier = Modifier.fillMaxSize(),
                            onClicked = { backStack.add(MainNavigation) }
                        )
                    }

                    entry<MainNavigation> {
                        ContentScreen(
                            onDetailBackStackReady = onDetailBackStackReady,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            )
        }
    }
}

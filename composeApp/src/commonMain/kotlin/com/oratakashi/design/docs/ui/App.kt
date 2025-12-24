package com.oratakashi.design.docs.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oratakashi.design.docs.navigation.HomeNavigation
import com.oratakashi.design.docs.navigation.MainNavigation
import com.oratakashi.design.docs.ui.screen.content.ContentScreen
import com.oratakashi.design.docs.ui.screen.home.HomeScreen
import com.oratakashi.design.foundation.OrataAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(
    hasDeeplink: Boolean = false,
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()
    OrataAppTheme(darkTheme = true) {
        LaunchedEffect(hasDeeplink) {
            if (hasDeeplink) {
                navController.navigate(MainNavigation)
            }
        }
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeNavigation,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                composable<HomeNavigation> {
                    HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        onClicked = { navController.navigate(MainNavigation) }
                    )
                }

                composable<MainNavigation> {
                    ContentScreen(
                        onNavHostReady = onNavHostReady,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
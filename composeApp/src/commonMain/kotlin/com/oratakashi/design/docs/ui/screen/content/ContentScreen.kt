package com.oratakashi.design.docs.ui.screen.content

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.VerticalDragHandle
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.rememberPaneExpansionState
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oratakashi.design.docs.helpers.NavigationHelpers
import com.oratakashi.design.docs.navigation.DefaultNavigation
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.navigation.page.ButtonNavigation
import com.oratakashi.design.docs.navigation.page.ColorSystemNavigation
import com.oratakashi.design.docs.navigation.page.ConfigurationNavigation
import com.oratakashi.design.docs.navigation.page.InstallationNavigation
import com.oratakashi.design.docs.navigation.page.SnackbarNavigation
import com.oratakashi.design.docs.navigation.page.TextFieldNavigation
import com.oratakashi.design.docs.navigation.page.TypographyNavigation
import com.oratakashi.design.docs.ui.component.sidebar.Sidebar
import com.oratakashi.design.docs.ui.screen.content.alert.AlertScreen
import com.oratakashi.design.docs.ui.screen.content.anchortext.AnchorTextScreen
import com.oratakashi.design.docs.ui.screen.content.button.ButtonScreen
import com.oratakashi.design.docs.ui.screen.content.colorsystem.ColorSystemScreen
import com.oratakashi.design.docs.ui.screen.content.configuration.ConfigurationScreen
import com.oratakashi.design.docs.ui.screen.content.installation.InstallationScreen
import com.oratakashi.design.docs.ui.screen.content.snackbar.SnackbarScreen
import com.oratakashi.design.docs.ui.screen.content.textfield.TextFieldScreen
import com.oratakashi.design.docs.ui.screen.content.typography.TypographyScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ContentScreen(
    onNavHostReady: suspend (NavController) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    var isNavHostReady by remember { mutableStateOf(false) }
    var currentRoute by remember { mutableStateOf(navController.currentDestination?.route) }

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    coroutineScope.launch {
        navController.currentBackStack.collect {
            val route = it.lastOrNull()?.destination?.route
            currentRoute = route
        }
    }

    BackHandler(
        enabled = navigator.canNavigateBack(BackNavigationBehavior.PopUntilContentChange)
    ) {
        navigateBack(
            coroutineScope = coroutineScope,
            navigator = navigator,
            navController = navController
        )
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        modifier = modifier,
        paneExpansionState = rememberPaneExpansionState(navigator.scaffoldValue),
        paneExpansionDragHandle = { state ->
            val interactionSource =
                remember { MutableInteractionSource() }
            VerticalDragHandle(
                modifier =
                    Modifier.paneExpansionDraggable(
                        state,
                        LocalMinimumInteractiveComponentSize.current,
                        interactionSource
                    ), interactionSource = interactionSource
            )
        },
        listPane = {
            AnimatedPane {
                val initialState = NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)
                Sidebar(
                    isDetailShow = initialState,
                    currentRoute = currentRoute,
                    onSidebarClick = {
                        // Only navigate if the clicked item is different from current route
                        if (it?.route != currentRoute) {
                            coroutineScope.launch {
                                navigator.navigateTo(ThreePaneScaffoldRole.Primary, it?.route)

//                                if (isNavHostReady) {
//                                    navController.navigate(it?.route.orEmpty()) {
//                                        launchSingleTop = true
//                                    }
//                                }
                            }
                        }
                    }
                )
            }
        },
        detailPane = {

            AnimatedPane {
                val showBack = !NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)
                val currentRoute = navigator.currentDestination?.contentKey
                val backAction = remember {
                    {
                        navigateBack(
                            navController = navController,
                            navigator = navigator,
                            coroutineScope = coroutineScope
                        )
                    }
                }

                LaunchedEffect(currentRoute) {
                    if (!currentRoute.isNullOrEmpty()) {
                        navController.navigate(currentRoute) {
                            launchSingleTop = true
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = if (showBack) DefaultNavigation else InstallationNavigation,
                    enterTransition = { fadeIn() },
                    exitTransition = { fadeOut() },
                ) {
                    isNavHostReady = true

                    composable<DefaultNavigation> {
                        Box(modifier = Modifier.fillMaxWidth()) {}
                    }

                    composable<InstallationNavigation> {
                        InstallationScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<ConfigurationNavigation> {
                        ConfigurationScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<ColorSystemNavigation> {
                        ColorSystemScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<TypographyNavigation> {
                        TypographyScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<AlertNavigation> {
                        AlertScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<AnchorTextNavigation> {
                        AnchorTextScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<ButtonNavigation> {
                        ButtonScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<SnackbarNavigation> {
                        SnackbarScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }

                    composable<TextFieldNavigation> {
                        TextFieldScreen(
                            showBack = showBack,
                            onBackClick = backAction
                        )
                    }
                }
            }
        }

    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
internal fun navigateBack(
    navController: NavController,
    navigator: ThreePaneScaffoldNavigator<String?>,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        navigator.navigateBack(BackNavigationBehavior.PopUntilContentChange)
        navController.navigateUp()
    }
}
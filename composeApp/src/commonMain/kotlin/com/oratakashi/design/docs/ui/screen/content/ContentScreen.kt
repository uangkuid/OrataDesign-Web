package com.oratakashi.design.docs.ui.screen.content

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
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.oratakashi.design.docs.helpers.NavigationHelpers
import com.oratakashi.design.docs.navigation.BaseNavigation
import com.oratakashi.design.docs.navigation.DefaultNavigation
import com.oratakashi.design.docs.navigation.navigationConfig
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

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ContentScreen(
    onDetailBackStackReady: (NavBackStack<NavKey>) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()
    val detailBackStack = rememberNavBackStack(navigationConfig, DefaultNavigation)
    val topEntry = detailBackStack.lastOrNull()
    val currentRoute = (topEntry as? BaseNavigation)?.route

    LaunchedEffect(Unit) {
        onDetailBackStackReady(detailBackStack)
    }

    // Single source of truth for navigator's pane-role state. Runs whenever detailBackStack's
    // top entry changes for ANY reason: sidebar click (forward), the in-app BackHandler
    // (backward), or ChronologicalBrowserNavigation mutating detailBackStack directly on browser
    // back/forward (web only - that library has no callback hook into navigator, it only
    // mutates the underlying SnapshotStateList).
    LaunchedEffect(topEntry) {
        if (topEntry != null && topEntry != DefaultNavigation) {
            navigator.navigateTo(ThreePaneScaffoldRole.Primary, currentRoute)
        } else {
            navigator.navigateTo(ThreePaneScaffoldRole.Secondary, null)
        }
    }

    val backAction = remember {
        {
            if (detailBackStack.size > 1) {
                detailBackStack.removeAt(detailBackStack.lastIndex)
            }
            Unit
        }
    }

    BackHandler(
        enabled = detailBackStack.size > 1
    ) {
        backAction()
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
                            detailBackStack.clear()
                            detailBackStack.add(DefaultNavigation)
                            if (it != null) {
                                detailBackStack.add(it)
                            }
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                val showBack = !NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)

                NavDisplay(
                    backStack = detailBackStack,
                    entryProvider = entryProvider {
                        entry<DefaultNavigation> {
                            Box(modifier = Modifier.fillMaxWidth()) {}
                        }

                        entry<InstallationNavigation> {
                            InstallationScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<ConfigurationNavigation> {
                            ConfigurationScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<ColorSystemNavigation> {
                            ColorSystemScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<TypographyNavigation> {
                            TypographyScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<AlertNavigation> {
                            AlertScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<AnchorTextNavigation> {
                            AnchorTextScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<ButtonNavigation> {
                            ButtonScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<SnackbarNavigation> {
                            SnackbarScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }

                        entry<TextFieldNavigation> {
                            TextFieldScreen(
                                showBack = showBack,
                                onBackClick = backAction
                            )
                        }
                    }
                )
            }
        }

    )
}

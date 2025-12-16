package com.oratakashi.design.docs.ui.screen.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import com.oratakashi.design.docs.helpers.NavigationHelpers
import com.oratakashi.design.docs.navigation.InstallationNavigation
import com.oratakashi.design.docs.ui.component.sidebar.Sidebar
import com.oratakashi.design.docs.ui.screen.content.installation.InstallationScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(
        enabled = navigator.canNavigateBack(BackNavigationBehavior.PopUntilContentChange)
    ) {
        navigateBack(
            coroutineScope = coroutineScope,
            navigator = navigator
        )
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        modifier = modifier,
        listPane = {
            AnimatedPane {
                val initialState = remember { NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue) }
                Sidebar(
                    isDetailShow = initialState,
                    onSidebarClick = {
                        coroutineScope.launch {
                            navigator.navigateTo(ThreePaneScaffoldRole.Primary, it?.route)
                        }
                    }
                )
            }
        },
        detailPane = {

            if (NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)) {
                coroutineScope.launch { navigator.navigateTo(ThreePaneScaffoldRole.Primary, InstallationNavigation.route) }
            }

            AnimatedPane {
                val content = navigator.currentDestination?.contentKey

                AnimatedVisibility(
                    visible = content == InstallationNavigation.route
                ) {
                    InstallationScreen()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
internal fun navigateBack(
    navigator: ThreePaneScaffoldNavigator<String?>,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        navigator.navigateBack(BackNavigationBehavior.PopUntilCurrentDestinationChange)
    }
}
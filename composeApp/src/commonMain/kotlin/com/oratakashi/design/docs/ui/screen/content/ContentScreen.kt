package com.oratakashi.design.docs.ui.screen.content

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import com.oratakashi.design.docs.helpers.NavigationHelpers
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
                val initialState =
                    remember { NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue) }
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

            LaunchedEffect(Unit) {
                if (NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)) {
                    coroutineScope.launch {
                        navigator.navigateTo(
                            ThreePaneScaffoldRole.Primary,
                            InstallationNavigation.route
                        )
                    }
                }
            }

            AnimatedPane {
                val content = navigator.currentDestination?.contentKey
                val showBack = remember { !NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue) }
                val backAction = remember {
                    {
                        navigateBack(navigator, coroutineScope)
                    }
                }

                when (content) {
                    InstallationNavigation.route -> InstallationScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    ConfigurationNavigation.route -> ConfigurationScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    ColorSystemNavigation.route -> ColorSystemScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    TypographyNavigation.route -> TypographyScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    AlertNavigation.route -> AlertScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    AnchorTextNavigation.route -> AnchorTextScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    ButtonNavigation.route -> ButtonScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    SnackbarNavigation.route -> SnackbarScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )

                    TextFieldNavigation.route -> TextFieldScreen(
                        showBack = showBack,
                        onBackClick = backAction
                    )
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
        navigator.navigateBack(BackNavigationBehavior.PopUntilContentChange)
    }
}
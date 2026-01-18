package com.oratakashi.design.docs.ui.component.component_preview.code_editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.rememberPaneExpansionState
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.VerticalDragHandle
import androidx.compose.material3.VerticalDragHandleDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.data.model.code_sidebar.TemplateContent
import com.oratakashi.design.docs.data.model.code_sidebar.TemplateManifest
import com.oratakashi.design.docs.helpers.NavigationHelpers
import com.oratakashi.design.docs.navigation.BaseNavigation
import com.oratakashi.design.docs.ui.component.code.Code
import com.oratakashi.design.docs.ui.component.component_preview.PreviewType
import com.oratakashi.design.docs.ui.component.component_preview.platform.MacOSWindowControls
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme
import kotlinx.coroutines.launch
import oratadesign_web.composeapp.generated.resources.Res

/**
 * CodeEditor is a composable function that displays the code preview UI for the documentation system.
 *
 * @author oratakashi
 * @since 18 Jan 2026
 * @param navigation Navigation object for the current context.
 * @param isDark Whether dark mode is enabled.
 * @param onDarkModeChange Callback when dark mode is toggled.
 * @param templateManifest List of template manifests for code sidebar.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T : BaseNavigation> CodeEditor(
    navigation: T?,
    isDark: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    type: PreviewType = PreviewType.Default,
    templateManifest: List<TemplateManifest>
) {
    val coroutineScope = androidx.compose.runtime.rememberCoroutineScope()
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()
    val fileList = remember(templateManifest) {
        if (type is PreviewType.Variant) {
            templateManifest
                .firstOrNull { it.name == navigation?.title?.lowercase() }
                ?.variant?.firstOrNull { it.name == type.name }
                ?.content ?: emptyList()
        } else {
            templateManifest
                .firstOrNull { it.name == navigation?.title?.lowercase() }
                ?.content ?: emptyList()
        }
    }
    var selectedSidebar: TemplateContent? by remember(fileList) {
        mutableStateOf(null)
    }
    OrataAppTheme(darkTheme = isDark) {
        BoxWithConstraints(
            modifier = Modifier.background(OrataTheme.colors.surface)
        ) {
            val maxHeight = minOf(maxHeight, 800.dp)
            ListDetailPaneScaffold(
                modifier = Modifier.fillMaxWidth().height(maxHeight),
                directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                paneExpansionState = rememberPaneExpansionState(navigator.scaffoldValue),
                paneExpansionDragHandle = { state ->
                    val interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource()
                    VerticalDragHandle(
                        colors = VerticalDragHandleDefaults.colors(
                            color = OrataTheme.colors.surfaceContainer,
                            pressedColor = OrataTheme.colors.surfaceContainerLow,
                            draggedColor = OrataTheme.colors.surfaceContainerHigh
                        ),
                        modifier = Modifier.paneExpansionDraggable(
                            state,
                            LocalMinimumInteractiveComponentSize.current,
                            interactionSource
                        ),
                        interactionSource = interactionSource
                    )
                },
                listPane = {
                    AnimatedPane {
                        androidx.compose.foundation.layout.Column(
                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                        ) {
                            MacOSWindowControls(
                                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                            )
                            CodeSidebar(
                                darkMode = isDark,
                                selected = selectedSidebar,
                                onSidebarClick = {
                                    selectedSidebar = it
                                    coroutineScope.launch {
                                        navigator.navigateTo(
                                            ThreePaneScaffoldRole.Primary,
                                            it.filepath
                                        )
                                    }
                                },
                                onDarkModeChange = onDarkModeChange,
                                fileList = fileList,
                                modifier = Modifier.fillMaxWidth().weight(1f)
                            )
                        }
                    }
                },
                detailPane = {
                    AnimatedPane {
                        val showBack = !NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)
                        val currentRoute = navigator.currentDestination?.contentKey
                        LaunchedEffect(showBack) {
                            if (showBack) return@LaunchedEffect
                            selectedSidebar = fileList.firstOrNull()
                            navigator.navigateTo(ThreePaneScaffoldRole.Primary, selectedSidebar?.filepath)
                        }
                        if (!currentRoute.isNullOrEmpty()) {
                            var bytesCode by remember { mutableStateOf(ByteArray(0)) }
                            val selectedFile: TemplateContent? by remember(currentRoute) {
                                mutableStateOf(fileList.firstOrNull { it.filepath == currentRoute })
                            }
                            LaunchedEffect(currentRoute) {
                                bytesCode = Res.readBytes(selectedFile?.filepath.orEmpty())
                            }
                            Code(
                                fileName = fileList.firstOrNull { it.filepath == currentRoute }?.name.orEmpty(),
                                code = bytesCode.decodeToString(),
                                canExpand = false,
                                darkMode = isDark,
                                canScrolled = true,
                                onBackPress = if (!showBack) null else {
                                    {
                                        coroutineScope.launch {
                                            navigator.navigateBack()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}

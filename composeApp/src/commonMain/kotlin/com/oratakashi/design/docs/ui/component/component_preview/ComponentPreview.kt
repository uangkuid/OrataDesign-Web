package com.oratakashi.design.docs.ui.component.component_preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDragHandle
import androidx.compose.material3.VerticalDragHandleDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.rememberPaneExpansionState
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.data.model.code_sidebar.TemplateContent
import com.oratakashi.design.docs.data.model.code_sidebar.TemplateManifest
import com.oratakashi.design.docs.helpers.DateHelpers
import com.oratakashi.design.docs.helpers.NavigationHelpers
import com.oratakashi.design.docs.navigation.BaseNavigation
import com.oratakashi.design.docs.ui.component.code.Code
import com.oratakashi.design.docs.ui.component.component_preview.code_editor.CodeSidebar
import com.oratakashi.design.docs.ui.component.component_preview.platform.AndroidPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.DesktopPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.IosPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.MacOSWindowControls
import com.oratakashi.design.docs.ui.component.component_preview.platform.WebsitePlatform
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import oratadesign_web.composeapp.generated.resources.Res

/**
 * ComponentPreview is a composable function that provides a preview container for UI components with device and theme switching capabilities.
 * This function is designed for documentation and design system purposes, allowing users to preview components in different platforms and color modes.
 *
 * @author oratakashi
 * @since 03 Jan 2026
 * @param modifier Modifier for styling the preview container. Default is Modifier.
 * @param content Composable lambda that defines the UI component to be previewed.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T : BaseNavigation> ComponentPreview(
    navigation: T?,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    /**
     * State handling
     */
    var deviceType by remember { mutableStateOf(PreviewPlatform.Website.name) }
    var previewState by remember { mutableStateOf(PreviewState.Preview.name) }
    var isDark by remember { mutableStateOf(true) }
    val previewPagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { PreviewPlatform.entries.size }
    )
    val mainPagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { PreviewState.entries.size }
    )
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()

    /**
     * Prepare file list manifest state
     */
    var bytesFileManifest by remember {
        mutableStateOf(ByteArray(0))
    }
    var templateManifest by remember {
        mutableStateOf<List<TemplateManifest>>(emptyList())
    }

    LaunchedEffect(navigation) {
        bytesFileManifest = Res.readBytes("files/templates/manifest.json")
        if (bytesFileManifest.isNotEmpty()) {
            try {
                val jsonString = bytesFileManifest.decodeToString()
                templateManifest = Json.decodeFromString<List<TemplateManifest>>(jsonString)
                println(templateManifest)
            } catch (e: Exception) {
                println("Error parsing manifest: ${e.message}")
                templateManifest = emptyList()
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PreviewTabs(
            selectedTab = previewState,
            onTabSelected = {
                previewState = it
                coroutineScope.launch {
                    mainPagerState.scrollToPage(PreviewState.valueOf(it).ordinal)
                }
            }
        )

        Card(
            colors = CardDefaults.cardColors(
                containerColor = OrataTheme.colors.surface,
                contentColor = OrataTheme.colors.onSurface,
            ),
            border = BorderStroke(
                width = 2.dp,
                color = OrataTheme.colors.outline
            ),
        ) {
            HorizontalPager(
                state = mainPagerState,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                when (it) {
                    PreviewState.Preview.ordinal -> {
                        BoxWithConstraints {
                            val isPlatformVisible = maxWidth > 700.dp

                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .padding(24.dp)
                            ) {
                                Row {
                                    AnimatedVisibility(isPlatformVisible) {
                                        PreviewTabs(
                                            PreviewPlatform.entries.map { it.name },
                                            selectedTab = deviceType,
                                            onTabSelected = {
                                                deviceType = it
                                                coroutineScope.launch {
                                                    previewPagerState.animateScrollToPage(
                                                        PreviewPlatform.valueOf(it).ordinal
                                                    )
                                                }
                                            }
                                        )
                                    }

                                    AnimatedVisibility(
                                        visible = isPlatformVisible,
                                        modifier = Modifier
                                            .weight(1f)
                                    ) {
                                        Spacer(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }

                                    PreviewTabs(
                                        tabs = listOf("Dark Mode", "Light Mode"),
                                        selectedTab = "Dark Mode",
                                        onTabSelected = {
                                            isDark = it == "Dark Mode"
                                        }
                                    )
                                }

                                HorizontalDivider()

                                HorizontalPager(
                                    state = previewPagerState,
                                    userScrollEnabled = false,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    when (it) {
                                        PreviewPlatform.Website.ordinal -> WebsitePlatform(
                                            isDark = isDark,
                                            content = content
                                        )

                                        PreviewPlatform.Desktop.ordinal -> DesktopPlatform(
                                            isDark = isDark,
                                            content = content
                                        )

                                        PreviewPlatform.Android.ordinal -> AndroidPlatform(
                                            isDark = isDark,
                                            content = content
                                        )

                                        else -> IosPlatform(
                                            isDark = isDark,
                                            content = content
                                        )

                                    }
                                }

                                HorizontalDivider()

                                Text(
                                    text = "© ${DateHelpers.getYear()} Orata Design System",
                                    style = OrataTheme.typography.labelMedium(),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    PreviewState.Code.ordinal -> {
                        val fileList = remember(templateManifest) {
                            templateManifest
                                .firstOrNull { it.name == navigation?.title?.lowercase() }
                                ?.content ?: emptyList()
                        }
                        var selectedSidebar : TemplateContent? by remember(fileList) {
                            mutableStateOf(null)
                        }
                        OrataAppTheme(
                            darkTheme = isDark
                        ) {
                            BoxWithConstraints(
                                modifier = Modifier
                                    .background(OrataTheme.colors.surface)
                            ) {
                                val maxHeight = minOf(maxHeight, 800.dp)
                                ListDetailPaneScaffold(
                                    modifier = Modifier.fillMaxWidth()
                                        .height(maxHeight),
                                    directive = navigator.scaffoldDirective,
                                    value = navigator.scaffoldValue,
                                    paneExpansionState = rememberPaneExpansionState(navigator.scaffoldValue),
                                    paneExpansionDragHandle = { state ->
                                        val interactionSource =
                                            remember { MutableInteractionSource() }
                                        VerticalDragHandle(
                                            colors = VerticalDragHandleDefaults.colors(
                                                color = OrataTheme.colors.surfaceContainer,
                                                pressedColor = OrataTheme.colors.surfaceContainerLow,
                                                draggedColor = OrataTheme.colors.surfaceContainerHigh
                                            ),
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
                                            val initialState =
                                                NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)

                                            Column(
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(16.dp))
                                            ) {
                                                MacOSWindowControls(
                                                    modifier = Modifier
                                                        .padding(
                                                            vertical = 16.dp,
                                                            horizontal = 16.dp
                                                        )
                                                )

                                                CodeSidebar(
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
                                                    fileList = fileList,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .weight(1f)
                                                )
                                            }
                                        }
                                    },
                                    detailPane = {
                                        AnimatedPane {
                                            val showBack =
                                                !NavigationHelpers.isListDetailPaneOpened(navigator.scaffoldValue)
                                            val currentRoute =
                                                navigator.currentDestination?.contentKey

                                            LaunchedEffect(showBack) {
                                                if (showBack) return@LaunchedEffect
                                                selectedSidebar = fileList.firstOrNull()
                                                navigator.navigateTo(ThreePaneScaffoldRole.Primary, selectedSidebar?.filepath)
                                            }

                                            if (!currentRoute.isNullOrEmpty()) {
                                                var bytesCode by remember {
                                                    mutableStateOf(ByteArray(0))
                                                }
                                                val selectedFile: TemplateContent? by remember(currentRoute) {
                                                    mutableStateOf(fileList.firstOrNull { it.filepath == currentRoute })
                                                }

                                                LaunchedEffect(currentRoute) {
                                                    println("currentRoute: $currentRoute")
                                                    println("filePath: ${selectedFile?.filepath.orEmpty()}")
                                                    bytesCode = Res.readBytes(selectedFile?.filepath.orEmpty())
                                                }

                                                Code(
                                                    fileName = fileList.firstOrNull { it.filepath == currentRoute }?.name.orEmpty(),
                                                    code = bytesCode.decodeToString(),
                                                    canExpand = false,
                                                    darkMode = isDark,
                                                    canScrolled = true
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.oratakashi.design.docs.ui.component.component_preview

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.data.model.code_sidebar.TemplateManifest
import com.oratakashi.design.docs.navigation.BaseNavigation
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.foundation.OrataTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import oratadesign_web.composeapp.generated.resources.Res
import com.oratakashi.design.docs.ui.component.component_preview.code_editor.CodeEditor
import com.oratakashi.design.docs.ui.component.component_preview.previewer.Previewer

/**
 * ComponentPreview is a composable function that provides a preview container for UI components with device and theme switching capabilities.
 * This function is designed for documentation and design system purposes, allowing users to preview components in different platforms and color modes.
 *
 * @author oratakashi
 * @since 03 Jan 2026
 * @param modifier Modifier for styling the preview container. Default is Modifier.
 * @param content Composable lambda that defines the UI component to be previewed.
 */
@Composable
fun <T : BaseNavigation> ComponentPreview(
    navigation: T?,
    type: PreviewType = PreviewType.Default,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

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

    var bytesFileManifest by remember { mutableStateOf(ByteArray(0)) }
    var templateManifest by remember { mutableStateOf<List<TemplateManifest>>(emptyList()) }

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
            ) { pageIndex ->
                when (pageIndex) {
                    PreviewState.Preview.ordinal -> Previewer(
                        deviceType = deviceType,
                        onDeviceTypeChange = { deviceType = it },
                        isDark = isDark,
                        onDarkModeChange = { isDark = it },
                        previewPagerState = previewPagerState,
                        coroutineScope = coroutineScope,
                        content = content
                    )

                    PreviewState.Code.ordinal -> {
                        CodeEditor(
                            navigation = navigation,
                            isDark = isDark,
                            onDarkModeChange = { isDark = it },
                            templateManifest = templateManifest,
                            type = type
                        )
                    }
                }
            }
        }
    }
}
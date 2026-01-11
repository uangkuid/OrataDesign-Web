package com.oratakashi.design.docs.ui.component.component_preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.helpers.DateHelpers
import com.oratakashi.design.docs.ui.component.component_preview.platform.AndroidPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.DesktopPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.IosPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.WebsitePlatform
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.foundation.OrataTheme
import kotlinx.coroutines.launch

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
fun ComponentPreview(
    modifier : Modifier = Modifier,
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PreviewTabs(
            selectedTab = previewState,
            onTabSelected = {
                previewState = it
                coroutineScope.launch {
                    mainPagerState.animateScrollToPage(PreviewState.valueOf(it).ordinal)
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
                state = mainPagerState
            ) {
                when(it) {
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
                                                    previewPagerState.animateScrollToPage(PreviewPlatform.valueOf(it).ordinal)
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
                                    when(it) {
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
                        BoxWithConstraints {

                        }
                    }
                }
            }
        }
    }
}
package com.oratakashi.design.docs.ui.component.component_preview.previewer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.oratakashi.design.docs.helpers.DateHelpers
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.docs.ui.component.component_preview.PreviewPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.WebsitePlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.DesktopPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.AndroidPlatform
import com.oratakashi.design.docs.ui.component.component_preview.platform.IosPlatform
import com.oratakashi.design.foundation.OrataTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Previewer is a composable function that displays the component preview UI for different platforms and color modes.
 *
 * @author oratakashi
 * @since 18 Jan 2026
 * @param deviceType The currently selected device/platform type.
 * @param onDeviceTypeChange Callback when the device/platform type changes.
 * @param isDark Whether dark mode is enabled.
 * @param onDarkModeChange Callback when dark mode is toggled.
 * @param previewPagerState Pager state for platform switching.
 * @param coroutineScope Coroutine scope for animations.
 * @param content The composable content to preview.
 */
@Composable
fun Previewer(
    deviceType: String,
    onDeviceTypeChange: (String) -> Unit,
    isDark: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    previewPagerState: PagerState,
    coroutineScope: CoroutineScope,
    content: @Composable ColumnScope.() -> Unit
) {
    BoxWithConstraints {
        val isPlatformVisible = maxWidth > 700.dp

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            Row {
                AnimatedVisibility(isPlatformVisible) {
                    PreviewTabs(
                        PreviewPlatform.entries.map { it.name },
                        selectedTab = deviceType,
                        onTabSelected = {
                            onDeviceTypeChange(it)
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
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth())
                }

                PreviewTabs(
                    tabs = listOf("Dark Mode", "Light Mode"),
                    selectedTab = if (isDark) "Dark Mode" else "Light Mode",
                    onTabSelected = {
                        onDarkModeChange(it == "Dark Mode")
                    }
                )
            }

            HorizontalDivider()

            HorizontalPager(
                state = previewPagerState,
                userScrollEnabled = false,
                modifier = Modifier.fillMaxWidth()
            ) { pageIndex ->
                when (pageIndex) {
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

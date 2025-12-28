package com.oratakashi.design.docs.ui.component.color_roles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs

@Composable
fun ColorRolesPreview(
    roles: ColorRoles,
    description: @Composable ColumnScope.() -> Unit = {},
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )
    var isPreview by remember { mutableStateOf(true) }
    var isDark by remember { mutableStateOf(true) }

    BoxWithConstraints(modifier = modifier) {
        val isWideScreen = maxWidth > 800.dp

        if (isWideScreen) {
            Row {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f),
                    content = description
                )
                PreviewContent(
                    isPreview = isPreview,
                    isDark = isDark,
                    pagerState = pagerState,
                    onPreviewChange = { isPreview = it == "Preview" },
                    onModeChange = { isDark = it == "Dark Mode" },
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    content = description
                )
                PreviewContent(
                    isPreview = isPreview,
                    isDark = isDark,
                    pagerState = pagerState,
                    onPreviewChange = { isPreview = it == "Preview" },
                    onModeChange = { isDark = it == "Dark Mode" }
                )
            }
        }
    }
}

@Composable
private fun PreviewContent(
    isPreview: Boolean,
    isDark: Boolean,
    pagerState: androidx.compose.foundation.pager.PagerState,
    onPreviewChange: (String) -> Unit,
    onModeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        BoxWithConstraints {
            val showPreviewCode = maxWidth > 500.dp
            var selectedTabPreviews by remember { mutableStateOf("Preview") }

            Row {
                AnimatedVisibility(
                    visible = showPreviewCode
                ) {
                    PreviewTabs(
                        selectedTab = selectedTabPreviews,
                        onTabSelected = {
                            onPreviewChange.invoke(it)
                            selectedTabPreviews = it
                        }
                    )
                }

                LaunchedEffect(showPreviewCode) {
                    if (!showPreviewCode) {
                        selectedTabPreviews = "Preview"
                        pagerState.scrollToPage(0)
                    }
                }

                AnimatedVisibility(
                    visible = showPreviewCode,
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                AnimatedVisibility(
                    visible = isPreview
                ) {
                    PreviewTabs(
                        tabs = listOf("Dark Mode", "Light Mode"),
                        selectedTab = "Dark Mode",
                        onTabSelected = onModeChange
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {

            }
        }
    }
}
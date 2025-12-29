package com.oratakashi.design.docs.ui.component.color_roles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.code.Code
import com.oratakashi.design.docs.ui.component.tabs.PreviewTabs
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme
import kotlinx.coroutines.launch
import oratadesign_web.composeapp.generated.resources.Res

@Composable
fun ColorRolesPreview(
    roles: ColorRoles,
    description: @Composable ColumnScope.() -> Unit = {},
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    println("code: ${roles.code}")
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )
    var isPreview by remember { mutableStateOf(true) }
    var isDark by remember { mutableStateOf(true) }

    BoxWithConstraints(modifier = modifier) {
        val isWideScreen = maxWidth > 800.dp

        if (isWideScreen) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f),
                    content = description
                )
                PreviewContent(
                    role = roles,
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
                    role = roles,
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
    role: ColorRoles,
    pagerState: androidx.compose.foundation.pager.PagerState,
    onPreviewChange: (String) -> Unit,
    onModeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var bytesColorRoleCode by remember {
        mutableStateOf(ByteArray(0))
    }

    LaunchedEffect(Unit) {
        bytesColorRoleCode = Res.readBytes(role.code)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        BoxWithConstraints {
            val showPreviewCode = maxWidth > 500.dp

            Row {
                AnimatedVisibility(
                    visible = showPreviewCode
                ) {
                    PreviewTabs(
                        onTabSelected = {
                            onPreviewChange.invoke(it)
                            coroutineScope.launch {
                                pagerState.scrollToPage(
                                    when (it) {
                                        "Preview" -> 0
                                        "Code" -> 1
                                        else -> 0
                                    }
                                )
                            }
                        }
                    )
                }

                LaunchedEffect(showPreviewCode) {
                    if (!showPreviewCode) {
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
            if (it == 0) {
                OrataAppTheme(
                    darkTheme = isDark
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = OrataTheme.colors.surface,
                            contentColor = OrataTheme.colors.onSurface
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = OrataTheme.colors.outline
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(
                                horizontal = 42.dp,
                                vertical = 42.dp
                            )
                        ) {
                            role.content.invoke()
                        }
                    }
                }
            } else {
                Code(
                    fileName = role.code.split("/").last(),
                    code = bytesColorRoleCode.decodeToString(),
                    canExpand = true
                )
            }
        }
    }
}
package com.oratakashi.design.docs.ui.component.component_preview.platform

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronRight
import compose.icons.feathericons.Copy
import compose.icons.feathericons.Download
import compose.icons.feathericons.Plus
import compose.icons.feathericons.RefreshCw
import compose.icons.feathericons.RotateCw

@Composable
fun WebsitePlatform(
    isDark: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 700.dp),
    content: @Composable () -> Unit
) {
    OrataAppTheme(
        darkTheme = isDark
    ) {
        BoxWithConstraints (
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(OrataTheme.colors.secondaryContainer)
        ) {
            val isWideScreen = maxWidth > 1150.dp
            val isAddressBarVisible = maxWidth > 500.dp

            println("maxWidth: $maxWidth")

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = OrataTheme.colors.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 12.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .defaultMinSize(minHeight = 500.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 60.dp)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 12.dp
                            )
                    ) {
                        // macOS-style window controls
                        MacOSWindowControls()

                        AnimatedVisibility(isWideScreen) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(OrataTheme.colors.surfaceContainer)
                            ) {
                                IconButton(
                                    onClick = {},
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.ChevronLeft,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                VerticalDivider(
                                    modifier = Modifier
                                        .padding(vertical = 4.dp)
                                        .size(width = 1.dp, height = 24.dp),
                                    color = OrataTheme.colors.outlineVariant // Use a visible color from theme
                                )

                                IconButton(
                                    enabled = false,
                                    onClick = {}
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.ChevronRight,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                        AnimatedVisibility(
                            visible = isWideScreen,
                            modifier = Modifier.weight(1f)
                        ) {
                            Spacer(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        AnimatedVisibility(
                            visible = isAddressBarVisible,
                            modifier = Modifier
                                .weight(5f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(CircleShape)
                                    .height(46.dp)
                                    .background(OrataTheme.colors.surfaceContainer)
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = "design.oratakashi.com",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = FeatherIcons.RotateCw,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        AnimatedVisibility(
                            visible = isWideScreen,
                            modifier = Modifier.weight(1f)
                        ) {
                            Spacer(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        AnimatedVisibility(isWideScreen) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(OrataTheme.colors.surfaceContainer)
                            ) {
                                IconButton(
                                    onClick = {},
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.Download,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.Plus,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.Copy,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Content
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)) {
                        content.invoke()
                    }
                }
            }
        }
    }
}
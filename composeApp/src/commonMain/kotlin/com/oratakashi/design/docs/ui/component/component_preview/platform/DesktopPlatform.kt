package com.oratakashi.design.docs.ui.component.component_preview.platform

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.Surface
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
fun DesktopPlatform(
    isDark: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 800.dp),
    content: @Composable ColumnScope.() -> Unit
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
            val isAddressBarVisible = maxWidth > 500.dp

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
                            .background(OrataTheme.colors.surfaceContainerLow)
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 35.dp)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                    ) {
                        // macOS-style window controls
                        MacOSWindowControls()

                        AnimatedVisibility(
                            visible = isAddressBarVisible,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Orata Design",
                                style = OrataTheme.typography.labelLarge(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            content = content
                        )
                    }
                }
            }
        }
    }
}
package com.oratakashi.design.docs.ui.component.component_preview.platform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.helpers.DateHelpers
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.BarChart
import compose.icons.feathericons.BatteryCharging
import compose.icons.feathericons.Wifi

@Composable
fun AndroidPlatform(
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
            val isUltraWideScreen = maxWidth > 1400.dp
            val isWideScreen = maxWidth > 1200.dp
            val isLargeScreen = maxWidth > 1000.dp
            val isTabletScreen = maxWidth > 800.dp
            val isLargePhone = maxWidth > 500.dp
            val isMediumPhone = maxWidth > 300.dp

            println("maxWidth: $maxWidth")

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 12.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(
                        when {
                            isUltraWideScreen -> 0.25f
                            isWideScreen -> 0.28f
                            isLargeScreen -> 0.35f
                            isTabletScreen -> 0.4f
                            isLargePhone -> 0.6f
                            isMediumPhone -> 0.8f
                            else -> 0.95f
                        }
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 700.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(OrataTheme.colors.surface),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Surface(
                        modifier = Modifier.defaultMinSize(minHeight = 700.dp)
                    ) {
                        Column(
                            modifier = Modifier.defaultMinSize(minHeight = 700.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 8.dp,
                                        bottom = 16.dp
                                    )
                            ) {
                                Text(
                                    text = DateHelpers.getTime(),
                                    style = OrataTheme.typography.labelLarge()
                                )

                                Spacer(
                                    modifier = Modifier.weight(1f)
                                )

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.Wifi,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Icon(
                                        imageVector = FeatherIcons.BarChart,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Icon(
                                        imageVector = FeatherIcons.BatteryCharging,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 16.dp)
                                    .weight(1f),
                                content = content
                            )

                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 8.dp),
                                thickness = 4.dp
                            )
                        }
                    }
                }
            }
        }
    }
}
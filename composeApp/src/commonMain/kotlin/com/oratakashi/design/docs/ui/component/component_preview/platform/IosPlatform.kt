package com.oratakashi.design.docs.ui.component.component_preview.platform

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.oratakashi.design.docs.helpers.DateHelpers
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.BarChart
import compose.icons.feathericons.BatteryCharging
import compose.icons.feathericons.Wifi
import oratadesign_web.composeapp.generated.resources.Res
import oratadesign_web.composeapp.generated.resources.compose_multiplatform
import oratadesign_web.composeapp.generated.resources.ic_ios_battery
import oratadesign_web.composeapp.generated.resources.ic_ios_mobile_signal
import oratadesign_web.composeapp.generated.resources.ic_ios_wifi
import org.jetbrains.compose.resources.vectorResource

@Composable
fun IosPlatform(
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

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 12.dp
                ),
                shape = RoundedCornerShape(24.dp),
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
                        .clip(RoundedCornerShape(16.dp))
                        .background(OrataTheme.colors.surface),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Surface(
                        modifier = Modifier.defaultMinSize(minHeight = 700.dp)
                    ) {
                        Column(
                            modifier = Modifier.defaultMinSize(minHeight = 700.dp)
                        ) {
                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                val (tvTime, containerSystem, dynamicIsland) = createRefs()

                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                        .constrainAs(dynamicIsland) {
                                            top.linkTo(parent.top, 8.dp)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)

                                            width = Dimension.value(120.dp)
                                            height = Dimension.value(35.dp)
                                        }
                                )

                                Text(
                                    text = DateHelpers.getTime(),
                                    style = OrataTheme.typography.labelLarge(),
                                    modifier = Modifier.constrainAs(tvTime) {
                                        top.linkTo(dynamicIsland.top)
                                        bottom.linkTo(dynamicIsland.bottom)
                                        start.linkTo(parent.start, 16.dp)
                                        end.linkTo(dynamicIsland.start, 8.dp)

                                        width = Dimension.fillToConstraints
                                        height = Dimension.wrapContent
                                    }
                                )

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.End),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.constrainAs(containerSystem) {
                                        top.linkTo(dynamicIsland.top)
                                        bottom.linkTo(dynamicIsland.bottom)
                                        end.linkTo(parent.end, 16.dp)
                                        start.linkTo(dynamicIsland.end, 8.dp)

                                        width = Dimension.fillToConstraints
                                        height = Dimension.wrapContent
                                    }
                                ) {
                                    Icon(
                                        imageVector = vectorResource(Res.drawable.ic_ios_mobile_signal),
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Icon(
                                        imageVector = vectorResource(Res.drawable.ic_ios_wifi),
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Icon(
                                        imageVector = vectorResource(Res.drawable.ic_ios_battery),
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
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
                                    .fillMaxWidth(0.4f)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 8.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                thickness = 6.dp,
                                color = OrataTheme.colors.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
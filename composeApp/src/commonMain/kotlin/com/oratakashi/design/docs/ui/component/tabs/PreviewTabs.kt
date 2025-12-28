package com.oratakashi.design.docs.ui.component.tabs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oratakashi.design.foundation.OrataTheme
import kotlin.math.roundToInt

@Composable
fun PreviewTabs(
    tabs: List<String> = listOf("Preview", "Code"),
    modifier: Modifier = Modifier,
    selectedTab: String = tabs.firstOrNull().orEmpty(),
    onTabSelected: (String) -> Unit = {}
) {
    var currentTab by remember { mutableStateOf(selectedTab) }

    // Animate the selected index
    val selectedIndex = tabs.indexOf(currentTab)
    val animatedOffset by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = tween(durationMillis = 300),
        label = "tabOffset"
    )

    Box(
        modifier = modifier
            .background(
                color = OrataTheme.colors.surfaceContainer,
                shape = CircleShape
            )
            .padding(4.dp)
    ) {
        Layout(
            content = {
                // Background indicator
                Box(
                    modifier = Modifier
                        .layoutId("indicator")
                        .background(
                            color = OrataTheme.colors.surfaceContainerHigh,
                            shape = CircleShape
                        )
                )

                // Tab items
                tabs.forEachIndexed { index, tab ->
                    TabItem(
                        modifier = Modifier.layoutId("tab_$index"),
                        text = tab,
                        isSelected = currentTab == tab,
                        onClick = {
                            currentTab = tab
                            onTabSelected(tab)
                        }
                    )
                }
            }
        ) { measurables, constraints ->
            // Measure tabs
            val tabMeasurables = tabs.indices.map { index ->
                measurables.first { it.layoutId == "tab_$index" }
            }
            val tabPlaceables = tabMeasurables.map { it.measure(constraints) }

            // Calculate total width and height
            val totalWidth = tabPlaceables.sumOf { it.width }
            val maxHeight = tabPlaceables.maxOf { it.height }

            // Measure indicator with same size as selected tab
            val indicatorMeasurable = measurables.first { it.layoutId == "indicator" }
            val selectedTabWidth = tabPlaceables[selectedIndex].width
            val indicatorPlaceable = indicatorMeasurable.measure(
                Constraints.fixed(selectedTabWidth, maxHeight)
            )

            layout(totalWidth, maxHeight) {
                // Calculate indicator position dynamically for any number of tabs
                val indicatorX = if (animatedOffset % 1 != 0f) {
                    // Currently animating between tabs
                    val fromIndex = animatedOffset.toInt()
                    val toIndex = (fromIndex + 1).coerceAtMost(tabPlaceables.size - 1)
                    val progress = animatedOffset - fromIndex

                    // Calculate start position of fromIndex
                    val fromX = tabPlaceables.take(fromIndex).sumOf { it.width }
                    // Calculate start position of toIndex
                    val toX = tabPlaceables.take(toIndex).sumOf { it.width }

                    // Interpolate between positions
                    (fromX + (toX - fromX) * progress).roundToInt()
                } else {
                    // Static position
                    val currentIndex = animatedOffset.toInt()
                    tabPlaceables.take(currentIndex).sumOf { it.width }
                }

                // Place indicator
                indicatorPlaceable.place(indicatorX, 0)

                // Place tabs
                var xPosition = 0
                tabPlaceables.forEachIndexed { _, placeable ->
                    placeable.place(xPosition, 0)
                    xPosition += placeable.width
                }
            }
        }
    }
}

@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(horizontal = 24.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) OrataTheme.colors.onSurface else OrataTheme.colors.onSurfaceVariant,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
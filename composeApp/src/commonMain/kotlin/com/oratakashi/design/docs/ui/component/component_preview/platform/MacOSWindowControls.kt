package com.oratakashi.design.docs.ui.component.component_preview.platform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oratakashi.design.foundation.OrataTheme

/**
 * MacOSWindowControls is a composable function that displays the macOS-style window control buttons (red, yellow, green).
 * This component mimics the traffic light buttons found in macOS windows.
 *
 * @author oratakashi
 * @since 03 Jan 2026
 * @param modifier A Modifier for styling the row container of the window controls.
 * @param buttonSize The size of each circular button. Default is 12.dp
 * @param spacing The horizontal spacing between buttons. Default is 8.dp
 */
@Composable
fun MacOSWindowControls(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 12.dp,
    spacing: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        // Red button (Close)
        Box(
            modifier = Modifier
                .size(buttonSize)
                .clip(CircleShape)
                .background(OrataTheme.colors.error)
        )

        // Yellow button (Minimize)
        Box(
            modifier = Modifier
                .size(buttonSize)
                .clip(CircleShape)
                .background(OrataTheme.colors.warning)
        )

        // Green button (Maximize/Fullscreen)
        Box(
            modifier = Modifier
                .size(buttonSize)
                .clip(CircleShape)
                .background(OrataTheme.colors.success)
        )
    }
}


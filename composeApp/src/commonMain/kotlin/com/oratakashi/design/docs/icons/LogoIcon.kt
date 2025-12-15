package com.oratakashi.design.docs.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.oratakashi.design.app.icons.DynamicIcons

internal object LogoIcon : DynamicIcons {
    @Composable
    /**
     * Logo icon for the Orata Design System.
     * @author oratakashi
     * @since 15 Dec 2025
     */
    override fun icons(): ImageVector {
        return ImageVector.Builder(
            name = "LogoIcon",
            defaultWidth = 126.dp,
            defaultHeight = 155.dp,
            viewportWidth = 126f,
            viewportHeight = 155f
        ).apply {
            // Left vertical bar
            path(
                fill = SolidColor(Color(0xFF2D9CDB)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(36.81f, 123.17f)
                horizontalLineTo(25.91f)
                curveTo(11.6f, 123.17f, 0f, 113.32f, 0f, 101.18f)
                verticalLineTo(31.15f)
                horizontalLineTo(36.81f)
                verticalLineTo(123.17f)
                close()
            }

            // Right vertical bar
            path(
                fill = SolidColor(Color(0xFF2D9CDB)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(100.1f, 123.17f)
                horizontalLineTo(89.19f)
                verticalLineTo(31.15f)
                horizontalLineTo(126f)
                verticalLineTo(101.18f)
                curveTo(126f, 113.32f, 114.4f, 123.17f, 100.1f, 123.17f)
                close()
            }

            // Bottom horizontal bar
            path(
                fill = SolidColor(Color(0xFF2D9CDB)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(67.62f, 154.32f)
                horizontalLineTo(36.81f)
                verticalLineTo(123.17f)
                horizontalLineTo(89.54f)
                verticalLineTo(132.4f)
                curveTo(89.54f, 144.5f, 79.72f, 154.32f, 67.62f, 154.32f)
                close()
            }

            // Top horizontal bar
            path(
                fill = SolidColor(Color(0xFF2D9CDB)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(58.73f, 0f)
                horizontalLineTo(89.54f)
                verticalLineTo(31.15f)
                horizontalLineTo(36.81f)
                verticalLineTo(21.92f)
                curveTo(36.81f, 9.81f, 46.62f, 0f, 58.73f, 0f)
                close()
            }
        }.build()
    }
}


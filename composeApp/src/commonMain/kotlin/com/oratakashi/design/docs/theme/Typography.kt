package com.oratakashi.design.docs.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import com.oratakashi.design.foundation.OrataTheme
import com.oratakashi.design.foundation.typography.OrataDesignTypography

class Typography: OrataDesignTypography {

    @Composable
    fun isPreview() = LocalInspectionMode.current

    @Composable
    override fun displayLarge(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.displayLarge else OrataTheme.typography.displayLarge()
    }

    @Composable
    override fun displayMedium(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.displayMedium else OrataTheme.typography.displayMedium()
    }

    @Composable
    override fun displaySmall(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.displaySmall else OrataTheme.typography.displaySmall()
    }

    @Composable
    override fun headlineLarge(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.headlineLarge else OrataTheme.typography.headlineLarge()
    }

    @Composable
    override fun headlineMedium(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.headlineMedium else OrataTheme.typography.headlineMedium()
    }

    @Composable
    override fun headlineSmall(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.headlineSmall else OrataTheme.typography.headlineSmall()
    }

    @Composable
    override fun titleLarge(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.titleLarge else OrataTheme.typography.titleLarge()
    }

    @Composable
    override fun titleMedium(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.titleMedium else OrataTheme.typography.titleMedium()
    }

    @Composable
    override fun titleSmall(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.titleSmall else OrataTheme.typography.titleSmall()
    }

    @Composable
    override fun labelLarge(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.labelLarge else OrataTheme.typography.labelLarge()
    }

    @Composable
    override fun labelMedium(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.labelMedium else OrataTheme.typography.labelMedium()
    }

    @Composable
    override fun labelSmall(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.labelSmall else OrataTheme.typography.labelSmall()
    }

    @Composable
    override fun bodyLarge(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.bodyLarge else OrataTheme.typography.bodyLarge()
    }

    @Composable
    override fun bodyMedium(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.bodyMedium else OrataTheme.typography.bodyMedium()
    }

    @Composable
    override fun bodySmall(): TextStyle {
        return if (isPreview()) MaterialTheme.typography.bodySmall else OrataTheme.typography.bodySmall()
    }
}
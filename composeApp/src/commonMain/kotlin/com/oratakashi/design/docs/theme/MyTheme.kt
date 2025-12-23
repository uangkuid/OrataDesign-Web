package com.oratakashi.design.docs.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.color.OrataDesignColorScheme
import com.oratakashi.design.foundation.typography.OrataDesignTypography

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: OrataDesignColorScheme? = MyColor(),
    typography: OrataDesignTypography = MyTypography(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    OrataAppTheme(
        darkTheme = darkTheme,
        colorScheme = colorScheme,
        typography = typography,
        dynamicColor = dynamicColor,
        content = content
    )
}
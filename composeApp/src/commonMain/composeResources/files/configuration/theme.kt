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
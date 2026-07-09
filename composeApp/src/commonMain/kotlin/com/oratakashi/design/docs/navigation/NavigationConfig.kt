package com.oratakashi.design.docs.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.navigation.page.ButtonNavigation
import com.oratakashi.design.docs.navigation.page.ColorSystemNavigation
import com.oratakashi.design.docs.navigation.page.ConfigurationNavigation
import com.oratakashi.design.docs.navigation.page.InstallationNavigation
import com.oratakashi.design.docs.navigation.page.SnackbarNavigation
import com.oratakashi.design.docs.navigation.page.TextFieldNavigation
import com.oratakashi.design.docs.navigation.page.TypographyNavigation
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

/**
 * Registers every [BaseNavigation]/[NavKey] route for polymorphic (de)serialization.
 * Required because non-JVM targets (iOS, wasmJs) can't rely on reflection to
 * restore the Navigation 3 back stack.
 * @author oratakashi
 * @since 09 Jul 2026
 */
val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeNavigation::class, HomeNavigation.serializer())
            subclass(MainNavigation::class, MainNavigation.serializer())
            subclass(DefaultNavigation::class, DefaultNavigation.serializer())
            subclass(AlertNavigation::class, AlertNavigation.serializer())
            subclass(AnchorTextNavigation::class, AnchorTextNavigation.serializer())
            subclass(ButtonNavigation::class, ButtonNavigation.serializer())
            subclass(ColorSystemNavigation::class, ColorSystemNavigation.serializer())
            subclass(ConfigurationNavigation::class, ConfigurationNavigation.serializer())
            subclass(InstallationNavigation::class, InstallationNavigation.serializer())
            subclass(SnackbarNavigation::class, SnackbarNavigation.serializer())
            subclass(TextFieldNavigation::class, TextFieldNavigation.serializer())
            subclass(TypographyNavigation::class, TypographyNavigation.serializer())
        }
    }
}

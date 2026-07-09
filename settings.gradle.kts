rootProject.name = "OrataDesign-Web"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // org.jetbrains.androidx.navigation3:navigation3-ui and org.jetbrains.compose.material3.adaptive:adaptive-navigation3
        // (the Compose Multiplatform UI layer for Nav3) are not published to Maven Central / Google Maven yet.
        // navigation3-runtime (plain androidx.navigation3 group) and com.github.terrakok:navigation3-browser
        // ARE on Maven Central, verified via their published build files - no extra repo needed for those two.
        maven("https://packages.jetbrains.team/maven/p/cmp/dev")
    }
}

include(":composeApp")
include(":androidApp")

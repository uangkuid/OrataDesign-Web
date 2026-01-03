rootProject.name = "OrataDesign-Web"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.google.com")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        // Add Maven Central first to try resolving androidx artifacts
        mavenCentral()
        // Try direct Maven URL
        maven {
            url = uri("https://maven.google.com")
            isAllowInsecureProtocol = false
        }
        google()
    }
}

include(":composeApp")
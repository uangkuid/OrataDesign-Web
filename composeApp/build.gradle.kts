
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    androidLibrary {
        compileSdk = 36
        minSdk = 24
        namespace = "com.oratakashi.design.docs.libs"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    jvm()
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        androidMain.dependencies {
            api(compose.preview)
            api(libs.androidx.activity.compose)
            api(project.dependencies.platform("io.insert-koin:koin-bom:4.2.1"))
            api("io.insert-koin:koin-android")
        }
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(libs.material3)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.androidx.lifecycle.viewmodelCompose)
            api(libs.androidx.lifecycle.runtimeCompose)
            api(libs.navigation3.runtime)
            api(libs.navigation3.ui)
            api(libs.adaptive.navigation3)
            api(libs.lifecycle.viewmodel.navigation3)
            api(libs.kotlinx.serialization.json)
            api(libs.composeIcons.feather)
            api(libs.material.adaptive)
            api(libs.material.navigation.suite)
            api(libs.material.layout)
            api(libs.material.navigation)
            api(libs.ui.backhandler)
            api(libs.constraintlayout.compose.multiplatform)
            api(libs.designsystem)
            api(libs.kotlinx.datetime)
            api(libs.datatable.material3)
            api(libs.highlights)
            api(libs.ktor.client.cio)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.logging)
            api(libs.ktor.serialization.kotlinx.json)
            api(libs.ktor.serialization.kotlinx.xml)
            api(project.dependencies.platform("io.insert-koin:koin-bom:4.2.1"))
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
            api(libs.koin.compose.viewmodel.navigation)
        }
        commonTest.dependencies {
            api(libs.kotlin.test)
        }
        jvmMain.dependencies {
            api(compose.desktop.currentOs)
            api(libs.kotlinx.coroutinesSwing)
        }
        webMain.dependencies {
            api(libs.navigation3.browser)
        }
    }
}

//android {
//    namespace = "com.oratakashi.design.docs"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//
//    defaultConfig {
//        applicationId = "com.oratakashi.design.docs"
//        minSdk = libs.versions.android.minSdk.get().toInt()
//        targetSdk = libs.versions.android.targetSdk.get().toInt()
//        versionCode = 1
//        versionName = "1.0"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//}

compose.desktop {
    application {
        mainClass = "com.oratakashi.design.docs.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.oratakashi.design.docs"
            packageVersion = "1.0.0"
        }
    }
}

compose.resources {
    generateResClass = always
}

/**
 * Task to generate manifest.json for templates directory.
 * This task executes a Node.js script that scans all subdirectories in the templates folder
 * and creates a JSON manifest listing all files within each subdirectory.
 * 
 * @author oratakashi
 * @since 14 Jan 2026
 */
tasks.register<Exec>("generateTemplateManifest") {
    description = "Generates manifest.json for templates directory"
    group = "build"
    
    workingDir = projectDir

    // Find node executable - try common locations
    val nodeExecutable = listOf(
        "/usr/local/bin/node",
        "/opt/homebrew/bin/node",
        System.getenv("NODE_PATH")?.let { "$it/node" }
    ).firstOrNull { it != null && file(it).exists() } ?: "node"

    commandLine(nodeExecutable, "scripts/generateManifest.js")

    val templatesDir = file("src/commonMain/kotlin/com/oratakashi/design/docs/ui/templates")
    val outputFile = file("src/commonMain/composeResources/files/template/manifest.json")
    
    inputs.dir(templatesDir)
    outputs.file(outputFile)
}

// Make generateTemplateManifest run before compose resource generation
tasks.matching { 
    it.name == "generateComposeResClass" || 
    it.name.endsWith("GenerateComposeResClass")
}.configureEach {
    dependsOn("generateTemplateManifest")
}

// Add dependency for resource copying tasks
tasks.matching {
    it.name.contains("copyNonXmlValueResources")
}.configureEach {
    dependsOn("generateTemplateManifest")
}

// Run before JVM run tasks
tasks.matching { 
    it.name == "run" || 
    it.name == "jvmRun"
}.configureEach {
    dependsOn("generateTemplateManifest")
}

// Run before Wasm/JS browser run tasks
tasks.matching {
    it.name.contains("wasmJs") && it.name.contains("Run")
}.configureEach {
    dependsOn("generateTemplateManifest")
}



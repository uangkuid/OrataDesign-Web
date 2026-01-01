// import com.android.build.api.dsl.androidLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    // Commented out for WASM-only builds to avoid dl.google.com access issues
    // alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    // Commented out Android target for WASM-only builds
    // androidTarget {
    //     compilerOptions {
    //         jvmTarget.set(JvmTarget.JVM_11)
    //     }
    // }

    jvm()
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        // Commented out Android dependencies for WASM-only builds
        // androidMain.dependencies {
        //     implementation(compose.preview)
        //     implementation(libs.androidx.activity.compose)
        //     implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.1.1"))
        //     implementation("io.insert-koin:koin-android")
        // }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(libs.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.composeIcons.feather)
            implementation(libs.material.adaptive)
            implementation(libs.material.navigation.suite)
            implementation(libs.material.layout)
            implementation(libs.material.navigation)
            implementation(libs.ui.backhandler)
            implementation(libs.constraintlayout.compose.multiplatform)
            implementation(libs.designsystem)
            implementation(libs.kotlinx.datetime)
            implementation(libs.datatable.material3)
            implementation(libs.highlights)
            implementation(libs.ktor.client.cio)
            implementation("io.ktor:ktor-client-content-negotiation:3.3.3")
            implementation("io.ktor:ktor-client-logging:3.3.3")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.3")
            implementation("io.ktor:ktor-serialization-kotlinx-xml:3.3.3")
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.1.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-compose")
            implementation("io.insert-koin:koin-compose-viewmodel")
            implementation("io.insert-koin:koin-compose-viewmodel-navigation")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

// Commented out Android configuration for WASM-only builds
// android {
//     namespace = "com.oratakashi.design.docs"
//     compileSdk = libs.versions.android.compileSdk.get().toInt()
// 
//     defaultConfig {
//         applicationId = "com.oratakashi.design.docs"
//         minSdk = libs.versions.android.minSdk.get().toInt()
//         targetSdk = libs.versions.android.targetSdk.get().toInt()
//         versionCode = 1
//         versionName = "1.0"
//     }
//     packaging {
//         resources {
//             excludes += "/META-INF/{AL2.0,LGPL2.1}"
//         }
//     }
//     buildTypes {
//         getByName("release") {
//             isMinifyEnabled = false
//         }
//     }
//     compileOptions {
//         sourceCompatibility = JavaVersion.VERSION_11
//         targetCompatibility = JavaVersion.VERSION_11
//     }
// }

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

// Commented out Android-specific debug dependencies for WASM-only builds
// dependencies {
//     debugImplementation(compose.uiTooling)
// }


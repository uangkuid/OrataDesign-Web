<div align="center">

# 🎨 Orata Design System - Web

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-blue.svg?style=flat&logo=kotlin)](http://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.10.0--rc01-blue)](https://github.com/JetBrains/compose-multiplatform)

**Modern Cross-Platform Design System Documentation for Kotlin Multiplatform**

[Getting Started](#-getting-started) • [Features](#-features) • [Documentation](#-documentation) • [Contributing](#-contributing)

</div>

---

## 📖 About

**OrataDesign-Web** is the official documentation website for the [Orata Design System](https://github.com/uangkuid/OrataDesign) - a modern, cross-platform design system built with Kotlin Multiplatform and Compose. This web application provides comprehensive documentation, interactive examples, and usage guidelines for implementing the Orata Design System in your projects.

The documentation site showcases all available UI components, color systems, typography, and configuration options, making it easy for developers to understand and implement the design system across Android, iOS, Desktop, and Web platforms.

> **⚠️ Development Status**  
> Orata Design System is currently in an **early experimental stage** (Alpha). The API and components are subject to change. Use with caution in production environments.

## ✨ Features

- 📱 **Cross-Platform Support** - Runs on Android, Desktop (JVM), and Web (Wasm/JS)
- 🎨 **Interactive Component Gallery** - Explore all available UI components with live examples
- 📝 **Comprehensive Documentation** - Detailed installation and usage guides
- 🌙 **Dark Mode Support** - Built-in dark theme
- 🔍 **Code Examples** - Syntax-highlighted code snippets for easy implementation
- 🎯 **Component Categories** - Organized sections for:
  - 🎨 Color System
  - 📝 Typography
  - 🔘 Buttons
  - ⚠️ Alerts
  - 🔗 Anchor Text
  - 📊 Text Fields
  - 🍰 Snackbars
  - And more...

## 🛠 Tech Stack

This project is built with modern technologies:

- **[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)** - Cross-platform development
- **[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)** - Declarative UI framework
- **[Material 3](https://m3.material.io/)** - Material Design 3 components
- **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation)** - Navigation framework
- **[Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)** - JSON serialization
- **[Constraint Layout](https://github.com/androidx/constraintlayout)** - Advanced layouts
- **[Compose Hot Reload](https://github.com/JetBrains/compose-multiplatform)** - Fast development iteration

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

| Tool/Platform | Version Required |
|--------------|------------------|
| **Gradle** | 8.2 or newer |
| **JDK** | 11 or newer |
| **Android SDK** | Min SDK 24, Target SDK 36 |
| **IDE** | IntelliJ IDEA or Android Studio |

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/uangkuid/OrataDesign-Web.git
cd OrataDesign-Web
```

### Build and Run

#### 🌐 Web Application (Recommended)

**For Wasm target (faster, modern browsers):**

```bash
# macOS/Linux
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Windows
.\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
```

**For JS target (supports older browsers):**

```bash
# macOS/Linux
./gradlew :composeApp:jsBrowserDevelopmentRun

# Windows
.\gradlew.bat :composeApp:jsBrowserDevelopmentRun
```

#### 📱 Android Application

```bash
# macOS/Linux
./gradlew :composeApp:assembleDebug

# Windows
.\gradlew.bat :composeApp:assembleDebug
```

Or use the run configuration in your IDE's toolbar.

#### 🖥️ Desktop Application

```bash
# macOS/Linux
./gradlew :composeApp:run

# Windows
.\gradlew.bat :composeApp:run
```

## 🚢 Deployment

This project includes automated CI/CD for deploying to GitHub Pages.

### GitHub Pages Deployment

The application is automatically built and deployed to GitHub Pages on every push to the `main` branch.

**Workflow Configuration:**
- **Base Image**: Ubuntu with Java 17 (Gradle 8.14.3 compatible)
- **Build Command**: `./gradlew wasmJsBrowserDistribution`
- **Output Path**: `composeApp/build/dist/wasmJs/productionExecutable`
- **Root File**: `index.html`

**To enable GitHub Pages deployment:**

1. Go to your repository **Settings** → **Pages**
2. Under "Build and deployment", select **GitHub Actions** as the source
3. The workflow will automatically deploy on the next push to `main`

**Manual Deployment:**

You can also trigger a deployment manually:
1. Go to the **Actions** tab in your repository
2. Select the **Deploy to GitHub Pages** workflow
3. Click **Run workflow**

**Local Build for Production:**

To build the production bundle locally:

```bash
./gradlew wasmJsBrowserDistribution
```

The output will be available in `composeApp/build/dist/wasmJs/productionExecutable/`

## 📂 Project Structure

```
OrataDesign-Web/
├── .github/
│   └── workflows/
│       └── deploy-github-pages.yml  # GitHub Pages deployment workflow
├── composeApp/                    # Main application module
│   ├── scripts/
│   │   └── generateManifest.py   # Auto-generates template manifest
│   ├── src/
│   │   ├── androidMain/          # Android-specific code
│   │   ├── commonMain/           # Shared code across all platforms
│   │   │   ├── composeResources/
│   │   │   │   └── files/
│   │   │   │       └── template/
│   │   │   │           └── manifest.json  # Auto-generated file
│   │   │   └── kotlin/
│   │   │       └── com/oratakashi/design/docs/
│   │   │           ├── ui/       # UI components and screens
│   │   │           │   ├── screen/
│   │   │           │   │   ├── home/           # Home/landing screen
│   │   │           │   │   └── content/        # Documentation screens
│   │   │           │   │       ├── alert/      # Alert component docs
│   │   │           │   │       ├── button/     # Button component docs
│   │   │           │   │       ├── colorsystem/ # Color system docs
│   │   │           │   │       ├── textfield/  # TextField component docs
│   │   │           │   │       ├── typography/ # Typography docs
│   │   │           │   │       └── ...
│   │   │           │   ├── component/  # Reusable UI components
│   │   │           │   └── templates/  # Code templates for examples
│   │   │           ├── navigation/     # Navigation definitions
│   │   │           ├── theme/          # App theme configuration
│   │   │           └── icons/          # Custom icons
│   │   ├── jvmMain/              # Desktop-specific code
│   │   └── webMain/              # Web-specific code (Wasm/JS)
│   └── build.gradle.kts          # Module build configuration
├── gradle/                        # Gradle wrapper and dependencies
├── build.gradle.kts              # Root build configuration
└── settings.gradle.kts           # Project settings
```

### Automatic Template Manifest Generation

The project includes an automated system that generates a `manifest.json` file listing all template files:

**How it works:**
- A Python script (`composeApp/scripts/generateManifest.py`) scans the `templates` directory
- It creates a JSON file listing all files within each template subdirectory
- The Gradle task `generateTemplateManifest` automatically runs before any build or run command
- The manifest.json is auto-generated and should not be committed to git

**Example manifest output:**
```json
{
  "alert": [
    "Alert.kt",
    "AlertConfig.kt"
  ],
  "snackbar": [
    "main.kt",
    "config.yaml"
  ]
}
```

**To manually regenerate the manifest:**
```bash
# From the composeApp directory
python3 scripts/generateManifest.py

# Or using Gradle
./gradlew :composeApp:generateTemplateManifest
```

## 🎯 Using Orata Design System

To use the Orata Design System in your own project:

### 1. Add Dependency

Add the following to your `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation("com.oratakashi:design:0.0.1-Alpha")
}
```

### 2. Enable Jetifier

Add to your `gradle.properties`:

```properties
android.enableJetifier=true
```

### 3. Apply Theme

Wrap your app content with the Orata theme:

```kotlin
import com.oratakashi.design.foundation.OrataAppTheme

@Composable
fun App() {
    OrataAppTheme(darkTheme = true) {
        // Your app content
    }
}
```

For more detailed instructions, run the application and navigate to the **Installation** section.

## 📚 Documentation

The web application includes comprehensive documentation for:

- **Installation Guide** - Step-by-step setup instructions
- **Configuration** - Theme and styling configuration
- **Color System** - Color palette and usage guidelines
- **Typography** - Text styles and hierarchy
- **Components** - All available UI components with examples:
  - Buttons (Primary, Secondary, Tonal, Outlined)
  - Alerts (Info, Success, Warning, Error)
  - Text Fields
  - Snackbars
  - Anchor Text
  - And more...

Visit the live documentation at the home screen after running the application.

## 🤝 Contributing

Contributions are welcome! Whether it's:

- 🐛 Reporting bugs
- 💡 Suggesting new features
- 📝 Improving documentation
- 🔧 Submitting pull requests

Please feel free to open an issue or submit a pull request.

### Development Workflow

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is open source. Please check with the repository owner for license details.

## 🔗 Related Projects

- **[Orata Design System](https://github.com/uangkuid/OrataDesign)** - The core design system library

## 🙏 Acknowledgments

- Built with [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- Powered by [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)
- Design inspired by [Material Design 3](https://m3.material.io/)

## 📞 Contact & Support

- **Issues**: [GitHub Issues](https://github.com/uangkuid/OrataDesign-Web/issues)
- **Discussions**: [GitHub Discussions](https://github.com/uangkuid/OrataDesign-Web/discussions)

---

<div align="center">

**Made with ❤️ by [Oratakashi](https://github.com/uangkuid)**

Copyright © 2024 Oratakashi

</div>

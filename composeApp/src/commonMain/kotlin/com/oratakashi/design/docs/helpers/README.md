# Helper Functions

Helper functions for cross-platform operations across all supported platforms.

## Available Helpers

### 1. Clipboard Helpers

Helper functions for clipboard operations across all supported platforms.

#### Overview

The `ClipboardHelpers` object provides a cross-platform API for copying text to the system clipboard. It supports all platforms in this KMP project: Android, JVM (Desktop), wasmJs, and JS.

#### Usage

##### Basic Usage

```kotlin
import com.oratakashi.design.docs.helpers.ClipboardHelpers

// Copy text to clipboard
ClipboardHelpers.copyToClipboard("Hello, World!")
```

##### Platform-Specific Notes

###### Android
For Android, you need to initialize the clipboard helper with the application context. This is already done in `MainActivity.kt`:

```kotlin
import com.oratakashi.design.docs.helpers.ClipboardHelpers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize clipboard helper
        ClipboardHelpers.initClipboard(this)
        
        // Now you can use ClipboardHelpers.copyToClipboard() anywhere in your app
    }
}
```

###### JVM (Desktop)
No initialization required. The clipboard helper uses Java AWT Toolkit:

```kotlin
ClipboardHelpers.copyToClipboard("Desktop clipboard text")
```

###### Web (wasmJs and JS)
No initialization required. The clipboard helper uses the Web Clipboard API with a fallback for older browsers:

```kotlin
ClipboardHelpers.copyToClipboard("Web clipboard text")
```

#### Example in Compose

```kotlin
@Composable
fun CopyButton() {
    Button(onClick = {
        ClipboardHelpers.copyToClipboard("Text to copy")
    }) {
        Text("Copy to Clipboard")
    }
}
```

#### Implementation Details

- **Android**: Uses Android's `ClipboardManager` service
- **JVM**: Uses Java AWT `Toolkit.getDefaultToolkit().systemClipboard`
- **Web (wasmJs/JS)**: Uses Web Clipboard API with textarea fallback for compatibility

#### Error Handling

All implementations include error handling. If clipboard operations fail, an error message will be printed to the console, but the application will continue to run normally.

---

### 2. URL Helpers

Helper functions for opening URLs in external browsers across all supported platforms.

#### Overview

The `UrlHelpers` object provides a cross-platform API for opening URLs in external browsers. It supports all platforms in this KMP project: Android, JVM (Desktop), wasmJs, and JS.

For the web platform, URLs are opened in a new tab (since the app is already running in a browser).

#### Usage

##### Basic Usage

```kotlin
import com.oratakashi.design.docs.helpers.UrlHelpers

// Open URL in external browser
UrlHelpers.openUrl("https://www.example.com")
```

##### Platform-Specific Notes

###### Android
For Android, the context is automatically injected via Koin. The helper uses `Intent.ACTION_VIEW` to open URLs in the default browser:

```kotlin
// No initialization required for Android
UrlHelpers.openUrl("https://github.com/uangkuid/OrataDesign")
```

###### JVM (Desktop)
No initialization required. The helper uses Java AWT `Desktop.browse()` to open URLs in the system's default browser:

```kotlin
UrlHelpers.openUrl("https://kotlinlang.org")
```

###### Web (wasmJs and JS)
No initialization required. The helper uses `window.open()` with `_blank` target to open URLs in a new tab. Security features `noopener` and `noreferrer` are enabled by default:

```kotlin
UrlHelpers.openUrl("https://github.com")
```

#### Example in Compose

```kotlin
@Composable
fun ExternalLinkButton() {
    Button(onClick = {
        UrlHelpers.openUrl("https://github.com/uangkuid/OrataDesign")
    }) {
        Text("Open GitHub")
    }
}
```

#### Implementation Details

- **Android**: Uses Android's `Intent` with `ACTION_VIEW` and `FLAG_ACTIVITY_NEW_TASK`
- **JVM**: Uses Java AWT `Desktop.browse()` with proper platform support check
- **Web (wasmJs/JS)**: Uses `window.open()` with `_blank` target and security features (`noopener,noreferrer`)

#### Security Considerations

- **Web Platform**: The implementation uses `noopener` and `noreferrer` attributes to prevent the opened page from accessing the `window.opener` property and to prevent sending the referrer information, protecting against potential security vulnerabilities.
- **All Platforms**: URL validation should be performed by the caller before passing to this helper to prevent malicious URLs.

#### Error Handling

All implementations include error handling. If opening a URL fails, an error message will be printed to the console, but the application will continue to run normally.

---

## Author

@oratakashi

## Since

- Clipboard Helpers: 03 Jan 2026
- URL Helpers: 05 Jan 2026

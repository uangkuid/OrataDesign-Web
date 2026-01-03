# Clipboard Helpers

Helper functions for clipboard operations across all supported platforms.

## Overview

The `ClipboardHelpers` module provides a cross-platform API for copying text to the system clipboard. It supports all platforms in this KMP project: Android, JVM (Desktop), wasmJs, and JS.

## Usage

### Basic Usage

```kotlin
import com.oratakashi.design.docs.helpers.copyToClipboard

// Copy text to clipboard
copyToClipboard("Hello, World!")
```

### Platform-Specific Notes

#### Android
For Android, you need to initialize the clipboard helper with the application context. This is already done in `MainActivity.kt`:

```kotlin
import com.oratakashi.design.docs.helpers.initClipboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize clipboard helper
        initClipboard(this)
        
        // Now you can use copyToClipboard() anywhere in your app
    }
}
```

#### JVM (Desktop)
No initialization required. The clipboard helper uses Java AWT Toolkit:

```kotlin
copyToClipboard("Desktop clipboard text")
```

#### Web (wasmJs and JS)
No initialization required. The clipboard helper uses the Web Clipboard API with a fallback for older browsers:

```kotlin
copyToClipboard("Web clipboard text")
```

## Example in Compose

```kotlin
@Composable
fun CopyButton() {
    Button(onClick = {
        copyToClipboard("Text to copy")
    }) {
        Text("Copy to Clipboard")
    }
}
```

## Implementation Details

- **Android**: Uses Android's `ClipboardManager` service
- **JVM**: Uses Java AWT `Toolkit.getDefaultToolkit().systemClipboard`
- **Web (wasmJs/JS)**: Uses Web Clipboard API with textarea fallback for compatibility

## Error Handling

All implementations include error handling. If clipboard operations fail, an error message will be printed to the console, but the application will continue to run normally.

## Author

@oratakashi

## Since

03 Jan 2026

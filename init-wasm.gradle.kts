// This init script provides a workaround for building WASM-only targets
// when Google Maven repository is not accessible

allprojects {
    buildscript {
        repositories {
            // Try using a local Maven repository as fallback
            mavenLocal()
        }
    }
}

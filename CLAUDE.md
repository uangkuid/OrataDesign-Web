# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

`OrataDesign-Web` is the documentation/demo website for the [Orata Design System](https://github.com/uangkuid/OrataDesign) — a Kotlin Multiplatform + Compose Multiplatform component library. This repo consumes that library (`com.oratakashi:design`) and showcases every component with live previews, config controls, and copyable code snippets. Targets: Android (library module, not an app), JVM desktop, and Web (`wasmJs` primary, `js` secondary).

## Commands

```bash
# Web (primary target, recommended for local iteration)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun    # wasm, modern browsers
./gradlew :composeApp:jsBrowserDevelopmentRun         # legacy JS target
./gradlew wasmJsBrowserDistribution                   # production bundle -> composeApp/build/dist/wasmJs/productionExecutable

# Desktop
./gradlew :composeApp:run

# Android (composeApp is an Android *library*; androidApp is the thin app shell)
./gradlew :composeApp:assembleDebug

# Tests (commonTest only, minimal coverage today — see Testing Strategy below)
./gradlew :composeApp:allTests
./gradlew :composeApp:jvmTest

# Regenerate the template manifest by hand (normally auto-runs before build/run tasks)
./gradlew :composeApp:generateTemplateManifest
```

CI (`.github/workflows/build-test.yml`, runs on PRs into `main`) builds all three targets independently: `:composeApp:assembleDebug` (macOS runner), `:composeApp:packageDistributionForCurrentOS` (Windows runner), `wasmJsBrowserDistribution` (Ubuntu runner). If you can't build a given target locally, matching the exact command that runner uses is the fastest way to reason about whether CI will pass.

## Architecture

### Module layout
- `composeApp` — everything: UI, docs content, theming, navigation, data/domain layers. Source sets: `commonMain` (shared), `androidMain`, `jvmMain`, `wasmJsMain`, `jsMain`, `webMain` (shared between `wasmJs`/`js`), `commonTest`.
- `androidApp` — a nearly-empty shell module that just depends on `composeApp` to produce an installable Android app; almost never needs edits.

### Package structure (`com.oratakashi.design.docs`)
- `data` / `domain` — conventional clean-architecture split, but only used for the one real network feature (fetching Maven metadata for the Installation page's version picker: `data/remote/service/MavenApiService`, `domain/usecase/GetMavenMetadataUseCase`, `ui/screen/content/installation/MavenViewModel`). Everything else in the app is static/local — don't assume every screen needs this layering.
- `di/AppModule.kt` — single Koin module registry (`repositoryModule`, `useCaseModule`, `viewModelModule`, `apiModule`, `networkModule`); `provideModule()` is called from each platform's `main()`/`Application`.
- `navigation` — see "Navigation" below.
- `ui/screen/content/<component>` — one screen per docs page (e.g. `alert`, `button`, `colorsystem`, `typography`, `installation`, `configuration`). Each renders: description, a live `component_preview`, an `attribute_table`, and a code snippet sourced from `ui/templates`.
- `ui/templates/<component>/...` — actual runnable Kotlin source snippets (not example strings) shown as "copy this code" in the docs UI, organized in `variant/<name>/` subfolders per component. These are scanned at build time (see Template manifest below), so keep new template files real, compilable Kotlin.
- `ui/component` — reusable doc-site chrome (sidebar, tabs, code block, color swatches, attribute tables, spinners) — not part of the design system itself.
- `theme` — this app's own site theme (`MyTheme`/`MyColor`/`MyTypography`), separate from `OrataAppTheme` which comes from the design system library and wraps the whole `App()`.
- `helpers` — small `expect`/`actual` cross-platform utilities (clipboard, URL-open, date). See `helpers/README.md` for the per-platform implementation notes if touching these.
- `Config.kt` — single source of truth for the sidebar structure (`sidebarItem: List<SidebarSection>`), mapping labels to navigation routes. Adding a new docs page means adding an entry here in addition to the navigation wiring below.

### Navigation (Navigation 3 — pre-release, recently migrated)

This project was migrated from `navigation-compose` 2.9.2 to **Compose Multiplatform Navigation 3** (see `plan.md` / `handoff.md` for the full migration history and hard-won lessons — worth reading before touching navigation code). Key facts:

- Every route is a `@Serializable object FooNavigation : BaseNavigation` (see `navigation/BaseNavigation.kt`). `route`/`title` are derived from the serializer's `serialName` — no manual string routes.
- **New route checklist**: (1) add the `@Serializable object` under `navigation/page/`, (2) register it in `navigation/NavigationConfig.kt`'s `navigationConfig` polymorphic serializer module (required for non-JVM targets, which can't use reflection to restore the back stack), (3) add an `entry<T> { ... }` case in `ContentScreen.kt`'s `NavDisplay`, (4) add a `SidebarItem` to `Config.kt`.
- **Two nested back stacks**, not one: `ui/App.kt` owns the top-level one (`HomeNavigation` splash → `MainNavigation` docs shell), `ui/screen/content/ContentScreen.kt` owns the inner one (`detailBackStack`, bottom entry always `DefaultNavigation` = empty placeholder, then the actual doc page routes).
- `ContentScreen.kt` renders `ListDetailPaneScaffold` (adaptive list/detail) and keeps a `rememberListDetailPaneScaffoldNavigator` in sync with `detailBackStack` via a **single reactive `LaunchedEffect(topEntry)`** — this was a real bug (blank screen on mobile-web back button) caused by driving the navigator imperatively from multiple call sites instead of reactively from one. Don't reintroduce imperative `navigator.navigateTo(...)` calls outside that effect.
- Web deep-linking/back-forward uses `com.github.terrakok:navigation3-browser`'s `ChronologicalBrowserNavigation`, bound only to `detailBackStack` in `webMain/main.kt`. URLs use a **fragment** (`#slug`), not a query param — this changed during the migration.
- Nav3 artifacts on Compose Multiplatform are **pre-release and move fast**. `navigation3-runtime`/`navigation3-ui` and `com.github.terrakok:navigation3-browser` are on Maven Central; `adaptive-navigation3` and other JetBrains CMP Nav3 artifacts are only in the JetBrains dev repo (`https://packages.jetbrains.team/maven/p/cmp/dev`, already wired into `settings.gradle.kts`). If bumping these versions, verify the exact version string exists by fetching raw `maven-metadata.xml` `<version>` entries yourself rather than trusting a summarized fetch — this has been wrong before (see `handoff.md`).
- `entry<T>` (from `androidx.navigation3.runtime`) is a member function of `EntryProviderScope`, not a top-level import — only usable inside an `entryProvider { }` lambda.

### Template manifest generation
`composeApp/scripts/generateManifest.js` scans `ui/templates/` and writes `src/commonMain/composeResources/files/templates/manifest.json`, listing each component's snippet files so the docs UI knows what code to display/copy. Wired as a Gradle `Exec` task (`generateTemplateManifest`) that runs automatically before compose-resource generation, `run`/`jvmRun`, and any `wasmJs*Run` task. If you add/remove files under `ui/templates/`, the manifest regenerates on the next build — don't hand-edit `manifest.json`.

### Design system version
The consumed library version is pinned in `gradle/libs.versions.toml` as `design = "..."`. When the upstream [OrataDesign](https://github.com/uangkuid/OrataDesign) library ships new components/APIs this repo wants to document, bump that version first.

### Testing
There is effectively no test suite yet (`commonTest` has a single placeholder). Don't assume test coverage exists for a feature — verify manually via `wasmJsBrowserDevelopmentRun` or by reading code.

# Handoff — Nav3 Migration (branch `feat/nav3-migration`)

Living status doc for the Navigation 3 migration described in [`plan.md`](./plan.md). Update this file every time meaningful progress is made or CI fails, so a new session can pick up without re-exploring from scratch.

## Status as of 2026-07-09 (CI iteration 1 failed and fixed, iteration 2 pushed, not yet confirmed green)

**All code changes for the migration are written.** The working environment has no local Kotlin/JVM/Gradle toolchain, so verification is 100% via GitHub Actions CI on the PR the user opened manually (`gh` CLI is unavailable here).

**CI iteration 1 result (commit `4d075ca`): FAILED at dependency resolution**, before any Kotlin compilation happened. Job `build-web` failed at `:kotlinWasmNpmInstall` / `:composeApp:wasmJsNpmAggregated` with "Could not resolve all dependencies." The specific artifacts that could NOT be resolved:
- `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0-rc02`
- `org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:2.11.0-rc02`
- `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-navigation3:2.11.0-rc02`
- `org.jetbrains.compose.material3.adaptive:adaptive:1.3.0-beta03`
- `org.jetbrains.compose.material3.adaptive:adaptive-layout:1.3.0-beta03`
- `org.jetbrains.compose.material3.adaptive:adaptive-navigation:1.3.0-beta03`

Notably, **NOT** in the failure list: `navigation3-runtime`, `navigation3-ui`, `adaptive-navigation3`, `navigation3-browser` — those 4 new Nav3 artifacts resolved fine at the versions in `plan.md` (`1.1.1`, `1.1.1`, `1.3.0-beta03`, `1.1.0` respectively). So the earlier assumption that `material-adaptive`/`-layout`/`-navigation` share a release train with `adaptive-navigation3` (and would also have a `1.3.0-beta03`) was **wrong**, and bumping `androidx-lifecycle` to `2.11.0-rc02` to match `lifecycle-viewmodel-navigation3` was also **wrong** — none of those pre-existing artifacts actually publish those versions.

**Fix applied (commit after `4d075ca`)**: reverted `androidx-lifecycle` back to `2.10.0` and `material-adaptive`/`material-adaptive-layout`/`material-adaptive-navigation` back to `1.2.0` (their original, known-working values from before this migration started). Left `adaptive-navigation3`, `navigation3-ui`, `navigation3-runtime`, `navigation3-browser` untouched since those already resolved. `lifecycle-viewmodel-navigation3` still shares `version.ref = "androidx-lifecycle"`, now pointing at `2.10.0` — **not yet confirmed this specific artifact actually publishes a `2.10.0`**, that's the thing to check first in the next CI run if resolution still fails.

**Next step for whoever picks this up: check the next CI run on the PR.** If dependency resolution now succeeds, the next failures (if any) will likely be actual Kotlin compile errors (wrong package names etc., see "Assumptions" below) rather than resolution errors — a different, more informative class of failure to debug.

### Completed (task list, all marked done in this session)
1. Gradle repositories + dependencies (`settings.gradle.kts`, `gradle/libs.versions.toml`, `composeApp/build.gradle.kts`)
2. `BaseNavigation` → `NavKey` (`navigation/BaseNavigation.kt`)
3. `navigation/NavigationConfig.kt` (new file, `SavedStateConfiguration`)
4. `ui/App.kt` rewritten for `NavDisplay`/`rememberNavBackStack`
5. `ui/screen/content/ContentScreen.kt` rewritten — single `detailBackStack`, dead code removed
6. `webMain/main.kt` rewritten — `ChronologicalBrowserNavigation` from `navigation3-browser`
7. Old `navigation-compose` dependency removed; confirmed no leftover `androidx.navigation.*` imports; confirmed `material-navigation` (adaptive-navigation) still needed and kept, since `ContentScreen.kt` and `CodeEditor.kt` still use `rememberListDetailPaneScaffoldNavigator` for pane-layout purposes (unrelated to routing)
8. This file + `plan.md` written

### Not yet done
9. **Push branch, open (draft) PR against `main`, let `.github/workflows/build-test.yml` run.**
10. Iterate on CI failures until android/jvm/web all pass.
11. Manual functional testing (can't be done from this session — no browser/device) — see checklist at the bottom.

## Files changed this session

- `settings.gradle.kts` — added JetBrains dev repo (`packages.jetbrains.team/maven/p/cmp/dev`)
- `gradle/libs.versions.toml` — new versions/libraries for navigation3-runtime, navigation3-ui, adaptive-navigation3, navigation3-browser; bumped `androidx-lifecycle` and `material-adaptive`/`-layout`/`-navigation`; removed `navigation-compose`
- `composeApp/build.gradle.kts` — added new deps to `commonMain`, `navigation3-browser` to `webMain`; removed `navigation.compose`
- `composeApp/src/commonMain/kotlin/com/oratakashi/design/docs/navigation/BaseNavigation.kt` — now extends `NavKey`
- `composeApp/src/commonMain/kotlin/com/oratakashi/design/docs/navigation/NavigationConfig.kt` — **new file**
- `composeApp/src/commonMain/kotlin/com/oratakashi/design/docs/ui/App.kt` — full rewrite
- `composeApp/src/commonMain/kotlin/com/oratakashi/design/docs/ui/screen/content/ContentScreen.kt` — full rewrite
- `composeApp/src/webMain/kotlin/com/oratakashi/design/docs/main.kt` — full rewrite
- `plan.md`, `handoff.md` — new

No changes needed/made to: `androidApp/MainActivity.kt`, `composeApp/src/jvmMain/.../main.kt`, `Sidebar.kt`, `NavigationHelpers.kt`, `Config.kt`, `CodeEditor.kt`, `DetailContent.kt`, `SidebarItem.kt` — all still consume `BaseNavigation.route`/`.title` unchanged.

## Assumptions made without local compilation — check these FIRST if CI fails

These are ranked roughly by how likely they are to be wrong:

1. **Package names for Nav3 APIs** (`androidx.navigation3.runtime.NavKey`, `.entry`, `.entryProvider`, `.rememberNavBackStack`, `.NavBackStack`, `androidx.navigation3.ui.NavDisplay`) — inferred from Android docs (`androidx.navigation3.runtime.*`) plus the pattern this project already follows (JetBrains ports keep the original `androidx.*` package name even though the Maven groupId is `org.jetbrains.androidx.*`). Not verified against the actual `org.jetbrains.androidx.navigation3:navigation3-ui:1.1.1` artifact. If CI says "unresolved reference," this is the first place to look — check the actual class files/sources jar via `./gradlew :composeApp:dependencies` or decompiling the resolved jar.
2. **`SavedStateConfiguration` import path** (`androidx.savedstate.serialization.SavedStateConfiguration` in `NavigationConfig.kt`) — same caveat, inferred from the terrakok sample app's import list which IS verified (see below), so this one is actually higher-confidence than #1.
3. ~~**Version compatibility**: `adaptive-navigation3:1.3.0-beta03` bumped alongside `material-adaptive`/`-layout`/`-navigation`~~ **CONFIRMED WRONG by CI iteration 1** — those three do NOT share a release train with `adaptive-navigation3`. Reverted to `1.2.0` (their original value). `adaptive-navigation3` itself stays at `1.3.0-beta03` since that one did resolve.
4. ~~**`androidx-lifecycle` bump to `2.11.0-rc02`**~~ **CONFIRMED WRONG by CI iteration 1** — `lifecycle-viewmodel-compose`/`lifecycle-runtime-compose` don't publish `2.11.0-rc02`. Reverted `androidx-lifecycle` to `2.10.0`. **Still open**: whether `lifecycle-viewmodel-navigation3` (which shares this version ref) actually publishes a `2.10.0` — wasn't in the iteration-1 failure list at the wrong version, but hasn't been confirmed to resolve at the reverted version either since it's a new artifact this migration introduces. Check this first if the next CI run still fails on it specifically.
5. **`ContentScreen.kt` pane-role vs content back-stack split** — kept `navigator.navigateTo(ThreePaneScaffoldRole.Primary, it?.route)` unchanged from the old code (same role, same semantics) purely for layout-emphasis switching on narrow screens, while `detailBackStack` now owns content routing. This is a judgment call, not verified against actual runtime behavior — if on narrow/mobile layouts selecting a sidebar item doesn't switch to showing the detail pane, this is the place to check (may need `ThreePaneScaffoldRole.Secondary` instead of `Primary`, or additional handling).

## Verified with high confidence (fetched actual source, not just docs prose)

- `com.github.terrakok:navigation3-browser:1.1.0` **is on Maven Central** (confirmed via its own `build.gradle.kts`: `mavenPublishing { publishToMavenCentral() }`) — no JitPack repo needed, already removed that assumption from `settings.gradle.kts`.
- `androidx.navigation3:navigation3-runtime` (plain `androidx.navigation3` group, not `org.jetbrains.androidx.navigation3`) is what `navigation3-browser` itself depends on, confirmed via its `gradle/libs.versions.toml`.
- `com.github.terrakok.navigation3.browser` package, `ChronologicalBrowserNavigation(backStack, saveKey, restoreKey)` signature, `buildBrowserHistoryFragment`/`getBrowserHistoryFragmentName`/`getBrowserHistoryFragmentParameters` helpers — all read directly from the library's actual `.kt` source files on GitHub (`ChronologicalBrowserNavigation.kt`), plus a working sample app (`sample/composeApp/.../App.kt`) that shows `rememberNavBackStack`/`NavDisplay`/`entryProvider`/`entry<T>` used together with it, including `import androidx.savedstate.serialization.SavedStateConfiguration`.
- **This library uses the URL fragment (`#slug`), not query params.** The site's URLs will change shape after this migration: `?page=installation` → `#installation`. This is a real, user-visible behavior change (old bookmarked/shared links with `?page=` will land on the Home screen, not the deep-linked page) — worth flagging to whoever reviews the PR, and possibly worth a redirect/compat shim as a follow-up (out of scope for this migration itself).

## Manual testing checklist (to run once CI is green and there's a way to actually open the app)

- [ ] Desktop/Android: Home → Content, click all 9 sidebar routes (Installation, Configuration, Color System, Typography, Alert, Anchor Text, Button, Snackbar, TextField), sidebar highlight matches content
- [ ] Desktop/Android: system/hardware back button pops one level (page → blank list state), not the whole stack
- [ ] Desktop/Android: in-page back arrow (`showBack`/`onBackClick`) works
- [ ] Web: same 9-route click-through
- [ ] Web: browser back/forward buttons after 2-3 route changes stay in sync with content + `document.title`
- [ ] Web: loading `#installation` (and other slugs) directly via URL lands on the right page on fresh load
- [ ] Web: no console warnings/infinite loop from `ChronologicalBrowserNavigation` (it logs to `window.console` if double-bound or if a fragment can't be restored)
- [ ] Compose Hot Reload still works (plugin is enabled in this project)

## How to continue from here

Branch `feat/nav3-migration` is pushed and a PR is open (user opened it manually since `gh` CLI is unavailable in this session's environment). CI iteration 1 failed at dependency resolution (see Status above); the fix for that has been committed and pushed. Watch the next CI run on the PR:

```bash
gh pr checks --watch          # if gh becomes available
gh run view <run-id> --log-failed
# fix, commit, push, repeat. Update this file's "Status" section after each iteration.
```

If `gh` stays unavailable, the user needs to paste the next failing job's log (or its download URL, like they did for iteration 1) for the next session/turn to read via WebFetch.

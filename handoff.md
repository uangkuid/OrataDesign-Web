# Handoff — Nav3 Migration (branch `feat/nav3-migration`)

Living status doc for the Navigation 3 migration described in [`plan.md`](./plan.md). Update this file every time meaningful progress is made or CI fails, so a new session can pick up without re-exploring from scratch.

## Status as of 2026-07-09 (initial implementation pass, not yet CI-verified)

**All code changes for the migration are written**, but **nothing has been compiled** — the working environment for this session had no local Kotlin/JVM/Gradle toolchain, so every change below is based on careful reading of docs + verified source of the third-party `navigation3-browser` library, not an actual build. The very next step for whoever picks this up is: **push this branch, open a PR, and read the first CI run's errors.** Expect it to fail at least once; that's normal for this kind of dependency-channel-is-all-prerelease migration.

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
3. **Version compatibility**: `adaptive-navigation3:1.3.0-beta03` bumped alongside `material-adaptive`/`-layout`/`-navigation` to the same `1.3.0-beta03` — assumed these are released in lockstep because they share the Maven group `org.jetbrains.compose.material3.adaptive`, but never confirmed this exact version actually has all three artifacts published (only confirmed `adaptive-navigation3` itself has a `1.3.0-beta03`). If `material-adaptive:1.3.0-beta03` doesn't resolve, try dropping back to `1.2.0` for those three and keep only `adaptive-navigation3` on the beta channel.
4. **`androidx-lifecycle` bump to `2.11.0-rc02`** — verified this version exists in the JetBrains dev metadata, but did NOT verify `lifecycle-viewmodel-compose`/`lifecycle-runtime-compose` (already in use elsewhere in the app) actually publish a matching `2.11.0-rc02`. If not, may need to split `lifecycle-viewmodel-navigation3` back onto its own separate (possibly older/newer) version ref instead of sharing `androidx-lifecycle`.
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

```bash
git push -u origin feat/nav3-migration
gh pr create --draft --base main --title "Migrate to Compose Multiplatform Navigation 3" --body "See plan.md and handoff.md"
gh pr checks --watch
# on failure:
gh run view <run-id> --log-failed
# fix, commit, push, repeat. Update this file's "Status" section after each iteration.
```

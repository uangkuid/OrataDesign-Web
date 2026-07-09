# Migrasi Navigation-Compose 2.9.2 → Compose Multiplatform Navigation 3

## Context

Project ini (OrataDesign-Web, module `:composeApp` + `:androidApp`, target: android library, jvm, wasmJs) saat ini pakai `org.jetbrains.androidx.navigation:navigation-compose:2.9.2` dengan type-safe routes berbasis `kotlinx.serialization` (`composable<T>` + `NavController`). Navigation 3 (referensi: https://kotlinlang.org/docs/multiplatform/compose-navigation-3.html) adalah rewrite total: back stack jadi `SnapshotStateList<NavKey>`/`NavBackStack<NavKey>` yang dikelola sendiri (bukan `NavController`), dirender lewat `NavDisplay(backStack, entryProvider)`. Tujuannya menghilangkan ketergantungan pada `NavController`/`ThreePaneScaffoldNavigator` sync yang saat ini rumit dan sebagian dead code di `ContentScreen.kt`, sekaligus mengikuti arah resmi JetBrains untuk Compose Multiplatform.

Keputusan yang sudah dikonfirmasi user:
- Web deep-link (`?page=slug`) & sinkronisasi tombol back/forward browser akan pakai library `com.github.terrakok:navigation3-browser:1.1.0` (bukan hand-roll manual).
- Tidak ada fase spike terpisah — langsung eksekusi migrasi penuh; masalah kompatibilitas versi diselesaikan saat ditemukan, bukan divalidasi dulu di branch terpisah.
- Dikerjakan di branch baru (bukan `main`), dengan beberapa commit bertahap.
- Semua dependency terkait Navigation 3 (termasuk yang sudah ada seperti `material-adaptive`/`material-layout`/`material-navigation`) di-update ke versi yang direkomendasikan JetBrains untuk Nav3, bukan sekadar menambah artifact baru di samping versi lama.
- Environment kerja sesi awal tidak punya setup Kotlin/JVM lokal — kompilasi/verifikasi dilakukan lewat GitHub Actions CI (workflow `.github/workflows/build-test.yml`, trigger on `pull_request` ke `main`, build 3 target: Android/JVM/Web).

**Status implementasi & progres terkini: lihat [`handoff.md`](./handoff.md).**

> Catatan: beberapa nomor versi di dokumen ini (khususnya `material-adaptive`/`-layout`/`-navigation` dan `androidx-lifecycle`) adalah tebakan awal yang **terbukti salah di CI iterasi pertama** dan sudah direvert ke versi lama yang memang sudah teruji jalan. Versi final yang benar-benar resolve ada di `gradle/libs.versions.toml` itu sendiri dan riwayatnya di `handoff.md`, bukan di sini.

## Temuan penting soal versi Nav3 (hasil pengecekan repository langsung, bukan asumsi dari dokumentasi)

- Artifact `org.jetbrains.androidx.navigation3:navigation3-ui` dan `org.jetbrains.compose.material3.adaptive:adaptive-navigation3` **tidak ada di Maven Central/Google Maven** — di-host di repository dev JetBrains: `https://packages.jetbrains.team/maven/p/cmp/dev`. Repository ini wajib ditambah ke `settings.gradle.kts`.
- `androidx.navigation3:navigation3-runtime` (groupId polos, beda dari `navigation3-ui`) DAN `com.github.terrakok:navigation3-browser` **sudah ada di Maven Central** (dikonfirmasi lewat source `navigation3-browser/build.gradle.kts` yang publish via `publishToMavenCentral()` dan `gradle/libs.versions.toml` milik library tsb) — tidak perlu JitPack atau repo tambahan lain untuk keduanya.
- Versi yang dipakai (hasil cek `maven-metadata.xml` langsung, per 2026-07-09): `navigation3-ui`/`navigation3-runtime` = `1.1.1` (stable), `adaptive-navigation3` = `1.3.0-beta03` (belum ada rilis stabil), `lifecycle-viewmodel-navigation3` = disamakan dengan `androidx-lifecycle` yang di-bump ke `2.11.0-rc02`.
- **Navigation 3 di Compose Multiplatform sepenuhnya pre-release** — channel dev JetBrains update sangat sering, kalau versi di atas sudah tidak ada saat CI jalan, cek ulang `maven-metadata.xml` di `https://packages.jetbrains.team/maven/p/cmp/dev/...`.
- `navigation3-browser` API terverifikasi langsung dari source GitHub (`ChronologicalBrowserNavigation(backStack, saveKey, restoreKey)`, package `com.github.terrakok.navigation3.browser`) — pakai **URL fragment (`#slug`)**, BUKAN query param (`?page=slug`) seperti skema lama. Ini perubahan URL yang user-visible, lihat detail di `handoff.md`.

## State navigasi sebelum migrasi (untuk konteks historis)

- **Route model**: `BaseNavigation` interface (`navigation/BaseNavigation.kt`) — setiap route adalah `@Serializable object XyzNavigation : BaseNavigation`, route/title diturunkan dari `serialName`. 12 route: `HomeNavigation`, `MainNavigation`, `DefaultNavigation`, + 9 route halaman di `navigation/page/` (Alert, AnchorText, Button, ColorSystem, Configuration, Installation, Snackbar, TextField, Typography).
- **Dua NavHost bersarang**:
  1. `ui/App.kt` — `NavHost` top-level: `HomeNavigation` → `HomeScreen`, `MainNavigation` → `ContentScreen`.
  2. `ui/screen/content/ContentScreen.kt` — `NavHost` dalam, 10 route (Default + 9 halaman), di dalam `ListDetailPaneScaffold` yang disinkronkan manual dengan `ThreePaneScaffoldNavigator` via `LaunchedEffect`. Ada dead code (`navController.navigate` yang di-comment, `isNavHostReady` yang di-set tapi tak berguna) — dihapus saat migrasi.
- **Web deep-link** (`webMain/main.kt`): parse `?page=slug` dari URL, map ke route via `Config.sidebarItem`, pakai `navController.bindToBrowserNavigation` (experimental, terikat `NavController`).

## Rencana Implementasi

### 1. Git workflow & dokumentasi sesi
- Branch: `feat/nav3-migration` dari `main`.
- Commit bertahap: (a) dependency Gradle + repository, (b) route model + `SavedStateConfiguration`, (c) `App.kt`, (d) `ContentScreen.kt`, (e) web deep-link (`webMain/main.kt`), (f) cleanup dependency lama + dead code, (g) perbaikan hasil verifikasi CI.
- `plan.md` (file ini) — rencana lengkap, tersedia di repo.
- `handoff.md` — living-state doc, update tiap progres berarti / tiap CI gagal.

### 2. Repository & Dependency Gradle
`settings.gradle.kts`: tambah `maven("https://packages.jetbrains.team/maven/p/cmp/dev")` di `dependencyResolutionManagement.repositories`.

`gradle/libs.versions.toml` — versi baru: `navigation3 = "1.1.1"`, `adaptiveNavigation3 = "1.3.0-beta03"`, `navigation3Browser = "1.1.0"`; `androidx-lifecycle` di-bump `2.10.0` → `2.11.0-rc02`; `material-adaptive`/`material-adaptive-layout`/`material-adaptive-navigation` di-bump `1.2.0` → `1.3.0-beta03` (satu release train dengan `adaptive-navigation3`).

Library baru: `navigation3-runtime` (`androidx.navigation3:navigation3-runtime`), `navigation3-ui` (`org.jetbrains.androidx.navigation3:navigation3-ui`), `adaptive-navigation3` (`org.jetbrains.compose.material3.adaptive:adaptive-navigation3`), `lifecycle-viewmodel-navigation3` (`org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-navigation3`, pakai version.ref `androidx-lifecycle`), `navigation3-browser` (`com.github.terrakok:navigation3-browser`).

`composeApp/build.gradle.kts`: dependency baru ditambah ke `commonMain.dependencies` (kecuali `navigation3-browser` yang khusus `webMain.dependencies`, karena browser-only). `navigation-compose` (lama) dihapus sepenuhnya. `material-navigation`/`material-adaptive`/`material-layout` dipertahankan (versi di-bump) karena masih dipakai untuk primitif layout pane (`ListDetailPaneScaffold`, `rememberListDetailPaneScaffoldNavigator`) — bukan untuk routing lagi.

### 3. Route model → `NavKey`
`BaseNavigation` (`navigation/BaseNavigation.kt`) di-extend dari `NavKey` (`androidx.navigation3.runtime.NavKey`). Karena `NavKey` marker interface tanpa member wajib, ke-12 file route tidak perlu diedit — otomatis jadi `NavKey`.

### 4. `SavedStateConfiguration`
File baru `navigation/NavigationConfig.kt` — `val navigationConfig = SavedStateConfiguration { serializersModule = SerializersModule { polymorphic(NavKey::class) { subclass(...) x12 } } }`, registrasi eksplisit ke-12 route (bukan `subclassesOfSealed`, karena tersebar di 2 package).

### 5. `App.kt`
Signature baru: `App(onBackStackReady: (NavBackStack<NavKey>) -> Unit = {}, onDetailBackStackReady: (NavBackStack<NavKey>) -> Unit = {})`. `rememberNavController()` → `rememberNavBackStack(navigationConfig, HomeNavigation)`. `NavHost` → `NavDisplay(backStack, entryProvider { entry<HomeNavigation>{...}; entry<MainNavigation>{...} })`. `onDetailBackStackReady` diteruskan ke `ContentScreen` supaya platform code (web) bisa akses back stack detail.

### 6. `ContentScreen.kt`
Back stack tunggal (`detailBackStack = rememberNavBackStack(navigationConfig, DefaultNavigation)`) jadi sumber kebenaran seleksi sidebar, menggantikan `NavController` dalam. `ThreePaneScaffoldNavigator` (`rememberListDetailPaneScaffoldNavigator`) **dipertahankan** tapi HANYA untuk switching pane role (layout, narrow-screen list/detail emphasis) via `navigator.navigateTo(ThreePaneScaffoldRole.Primary, ...)` — bukan lagi untuk content routing. `onSidebarClick` mereset `detailBackStack` (clear + push Default + push target). `BackHandler` pop `detailBackStack` + panggil `navigator.navigateBack`. Dead code (`isNavHostReady`, comment block, `navigateBack()` helper lama) dihapus.

### 7. Web deep-link (`webMain/main.kt`)
`bindToBrowserNavigation` (NavController-based) diganti `ChronologicalBrowserNavigation(backStack, saveKey, restoreKey)` dari `navigation3-browser`, dibind ke `detailBackStack` milik `ContentScreen` (didapat lewat `onDetailBackStackReady`). Skema URL berubah dari `?page=slug` ke `#slug` (fragment-based) — konsekuensi dari API library ini, bukan pilihan desain.

### 8. Cleanup
Hapus `navigation-compose` dari Gradle. Pastikan tidak ada sisa `import androidx.navigation.*` (namespace lama). `androidApp/MainActivity.kt` & `composeApp/jvmMain/main.kt` tidak perlu diubah (parameter baru `App()` semua punya default).

### 9. Verifikasi — via GitHub Actions CI
Push branch → buka PR (boleh draft) → `build-test.yml` jalan otomatis (3 job: android/jvm/web). Cek `gh pr checks` / `gh run view --log-failed`, perbaiki, ulangi. Manual functional test (klik 9 route sidebar, back button, browser back/forward, deep-link via URL) dilakukan user setelah CI hijau — tidak bisa dilakukan dari sesi tanpa browser/device.

## Risiko yang Perlu Diperhatikan
- Semua dependency Nav3 pre-release — channel dev JetBrains berubah cepat, versi di plan ini bisa basi.
- Kompatibilitas versi antar `material3.adaptive` group (lama vs baru) dan `androidx.lifecycle` group — baru diketahui pasti lewat CI run pertama.
- `ChronologicalBrowserNavigation` mengubah skema URL (`?page=` → `#slug`) — breaking change untuk bookmark/link lama, perlu dikomunikasikan.
- Nama API persis (`rememberNavBackStack`, `NavDisplay`, `entry<T>`, `entryProvider`) diambil dari dokumentasi prosa + source sample pihak ketiga, bukan API reference resmi JetBrains — perlu dikonfirmasi di compile pertama.

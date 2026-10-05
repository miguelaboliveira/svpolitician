# AGENTS.md

This file provides guidance to AI coding agents when working with code in this repository.

## Project

Multi-module Android app (Kotlin, Hilt, SQLDelight, Retrofit, Jetpack Compose) that fetches phrases from the [Techy API](https://techy-api.vercel.app/), stores them locally, and shows them across Home / History / Settings tabs.

## Commands

```bash
./gradlew build                      # What CI runs (.github/workflows): compile, lint, kotlinter, all unit tests
./gradlew assembleDebug              # Build the debug APK
./gradlew test                       # All unit tests (JVM modules use `test`; Android modules have testDebugUnitTest etc.)
./gradlew :<android-module>:testDebugUnitTest --tests "<fully.qualified.TestClass>"   # Single test, Android module
./gradlew :<jvm-module>:test --tests "*.SomeTest.someMethod"                         # Single test, JVM module (e.g. :feature:home:domain)
./gradlew lintKotlin                 # kotlinter (ktlint) check
./gradlew formatKotlin               # kotlinter auto-format
./gradlew :app:lint                  # Android lint (uses app/lint-baseline.xml)
./gradlew connectedAndroidTest       # Instrumented tests (needs a device/emulator)
```

CI uses JDK 25; modules compile with a Kotlin JVM toolchain of 21. Configuration cache, build cache and parallel execution are enabled in `gradle.properties`, so build logic must stay configuration-cache compatible.

## Build conventions

- All modules apply a precompiled convention plugin from `build-logic/conventions/src/main/kotlin/` rather than configuring plugins directly:
  - `svpolitician-jvm-library` — pure Kotlin/JVM (domain, `public` APIs, fakes)
  - `svpolitician-android-library` → `-hilt` → `-compose` → `-feature` (feature = compose + hilt + Fragment/Navigation/SafeArgs deps)
  - `svpolitician-android-application` — `:app` only
  Shared logic lives in `buildlogic/*Config.kt` (`configureKotlin`, `configureAndroidCommon`, `configureCompose`, `configureHilt`).
- `configureKotlin()` enables **explicit API mode** and **`allWarningsAsErrors`** everywhere: every declaration needs an explicit visibility modifier (`public`/`internal`/`private`), and any compiler warning fails the build.
- Dependencies come from `gradle/libs.versions.toml`; module-to-module dependencies use type-safe accessors (`projects.core.database.public`). Convention plugins read the catalog via `libs.findLibrary(...)`.
- Kotlin style: `.editorconfig` sets 100-char lines; ktlint function naming is relaxed for `@Composable` functions. Generated SQLDelight sources are excluded from kotlinter.
- Android library modules set a `namespace` of `com.miguelaboliveira.svpolitician.<path>`; feature UI modules also set a `resourcePrefix` (e.g. `home`).
- Dependency versions are bumped by Renovate (`renovate.json`).

## Architecture

### Module layout (`settings.gradle.kts`)

- **`core/<name>/{public,impl,fake}`** — API/implementation split:
  - `public`: interfaces and models (JVM-only where possible). Features depend only on this.
  - `impl`: Android/Hilt implementation plus the `@Module @InstallIn(SingletonComponent::class)` that binds it. Only `:app` depends on `impl`.
  - `fake`: hand-written in-memory test doubles (e.g. `databaseFake()` uses an in-memory JDBC SQLite driver; `HttpApiFake`, `UserPreferencesStoreFake`). Use these in tests instead of mocking.
  - Applies to `database`, `network`, `userpreferences`.
- **`core/ephemeral/{core,android}`** — `EphemeralStore<S>`: a `StateFlow` that persists itself as JSON into a `SavedStateHandle` (`SavedStateHandle.ephemeralStore(initialState, key)`).
- **`core/ui/*`** — `design` (theme, colors, preview annotations), `fragmentext` (`svPoliticianComposeView { }` for hosting Compose inside a Fragment), `composeext` (Compose helpers), `error` (`UiError` + `ErrorSnackbar`).
- **`feature/<name>/{domain,ui}`** — `domain` is a JVM module with `@Inject`-constructed use cases (operator `invoke`) that talk to `core` public APIs; `ui` contains Fragment + Screen (composable) + UiState + `@HiltViewModel`.
- **`:app`** — wires everything: depends on all `impl` modules and feature `ui` modules, provides app-level values in `ApplicationModule` (`@Named("baseUrl")`, `versionName`, `versionCode`).

### Data flow

- Network fetch → persist → observe: use cases like `FetchPhraseUseCase` call `HttpApi` and insert into the SQLDelight DB; other use cases expose DB queries as `Flow`s (SQLDelight coroutine extensions). The DB is the single source of truth for UI.
- SQLDelight schema lives in `core/database/public/src/main/sqldelight/` (database `SVPoliticianDatabase`, `Instant` columns via `InstantColumnAdapter`). Both `DatabaseModule` (impl) and `databaseFake()` must construct the DB with the same adapters.

### UI pattern

- Navigation is **not** a NavGraph: `MainActivity` hosts a `ViewPager2` (swipe disabled) with one Fragment per tab, switched by a `BottomNavigationView` (`activity_main.xml`, `menu/bottom_navigation.xml`). Adding a tab means updating the adapter, the menu and the item-selected listener.
- Each feature Fragment is `@AndroidEntryPoint` and returns `svPoliticianComposeView { … }`, collecting the ViewModel's `uiState` with `collectAsStateWithLifecycle()` and passing callbacks to a stateless `*Screen` composable.
- ViewModels build a single `StateFlow<*UiState>` via `combine(...).stateIn(viewModelScope, WhileSubscribed(5000), initial)`. Errors are a list of `UiError` held in state and removed via a `consumeError(id)` callback after the snackbar shows them. Rethrow `CancellationException` when catching failures.

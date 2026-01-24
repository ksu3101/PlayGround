# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run all unit tests
./gradlew test

# Run tests for a specific module
./gradlew :feature:pokemon:test
./gradlew :core:domain:test

# Install git hooks (pre-commit with ktlint)
./gradlew app:preBuild

# Run detekt static analysis
./gradlew detekt
# Or with CLI: detekt -r html:build/reports/detekt.html
```

## Architecture

This is a multi-module Android app following Clean Architecture with MVVM pattern.

### Module Structure

- **app**: Main application module with navigation host (`PlayGroundNavHost`) and main screen
- **core/**: Shared library modules
  - `common`: Coroutine dispatchers (Main, IO) for DI
  - `data`: Repository and DataSource implementations
  - `domain`: UseCases consumed by features
  - `design`: Material Design 3 theme, colors, and Compose widgets (custom views only here)
  - `network`: Retrofit/OkHttp configuration
  - `test`: Shared test utilities (turbine, mockk, coroutines-test)
  - `annotation`: Custom annotations
- **feature/**: UI layer modules (Compose screens, ViewModels, navigation)
  - `pokemon`: Pokemon list/detail using PokeAPI
  - `gallery`: Material Design 3 component showcase
  - `google-billing`: Billing integration
- **build-logic/convention**: Gradle convention plugins to reduce boilerplate

### Convention Plugins

Apply in `build.gradle.kts`:
```kotlin
plugins {
    id("kr.swkang.playground.android.application")      // Android app config
    id("kr.swkang.playground.android.application.compose") // Compose for apps
    id("kr.swkang.playground.android.library")          // Android library config
    id("kr.swkang.playground.android.library.compose")  // Compose for libraries
    id("kr.swkang.playground.android.feature")          // Feature module config
    id("kr.swkang.playground.android.hilt")             // Hilt DI setup
    id("kr.swkang.playground.android.test")             // Test configuration
}
```

### Navigation Pattern

Each feature module exposes navigation extensions:
- `NavController.navigateTo*()` - navigation functions
- `NavGraphBuilder.*Screen()` - composable route definitions

Example: `feature/pokemon/navigation/PokeNavigation.kt` exposes `navigateToPoke()` and `pokeMainScreen()`.

All routes are registered in `app/.../PlayGroundNavHost.kt`.

### Dependency Injection

Hilt is used throughout. ViewModels use `@HiltViewModel`, repositories use `@Singleton` modules in `di/` packages.

## Code Quality

- **ktlint**: Enforced via pre-commit hook (installed by `app:preBuild` task). Requires ktlint CLI installed.
- **detekt**: Static analysis configured in root `build.gradle.kts`
- **compose-lint**: Slack's compose lint checks applied to app module

## Tech Stack

- Kotlin, Coroutines, Flow
- Jetpack Compose with Material Design 3
- Hilt for DI
- Retrofit + Moshi for networking
- Paging 3 for pagination
- Coil for image loading
- Unit tests: JUnit4, MockK, Turbine (Flow testing)
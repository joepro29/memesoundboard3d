# Fix Gradle Sync Error: DependencyHandler.module

The project is failing to sync because it uses Android Gradle Plugin (AGP) 8.1.0 with Gradle 9.3.0. AGP 8.1.0 is incompatible with Gradle 9.x because it relies on APIs (specifically `DependencyHandler.module(Object)`) that were removed in Gradle 9.0.

Additionally, the project is missing the Gradle Wrapper files (`gradlew`, `gradle-wrapper.properties`), which causes the IDE to fall back to its default (and incompatible) Gradle version.

## Proposed Changes

### Build Configuration

#### [NEW] [gradle-wrapper.properties](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/gradle/wrapper/gradle-wrapper.properties)
Create this file to pin the Gradle version to **8.4**, which is compatible with AGP 8.1.x.

#### [MODIFY] [build.gradle](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/build.gradle)
- Update AGP from `8.1.0` to `8.1.4` for stability.
- Update Kotlin from `1.9.0` to `1.9.24`.
- Refactor the `clean` task to use the modern `tasks.register` syntax to avoid deprecation warnings.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to verify that the project now syncs successfully.
- Run `gradle_build("help")` to ensure the build configuration is valid.

### Manual Verification
- Verify that the error `'org.gradle.api.artifacts.Dependency org.gradle.api.artifacts.dsl.DependencyHandler.module(java.lang.Object)'` no longer appears during sync.

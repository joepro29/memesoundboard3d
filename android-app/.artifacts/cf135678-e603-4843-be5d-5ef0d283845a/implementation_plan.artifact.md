# Fix missing launcher icons

The build is failing because `AndroidManifest.xml` references launcher icons (`@mipmap/ic_launcher` and `@mipmap/ic_launcher_round`) that do not exist in the project's resource directories.

## Proposed Changes

### [app]

I will create a set of adaptive launcher icons to satisfy the build requirements and provide a basic visual identity for the app.

#### [NEW] [ic_launcher_background.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/values/colors.xml) (Actually I'll use a color resource for background, or create a drawable)

I will create a background drawable:
#### [NEW] [ic_launcher_background.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/drawable/ic_launcher_background.xml)
A simple vector drawable using the `primary` color.

#### [NEW] [ic_launcher_foreground.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/drawable/ic_launcher_foreground.xml)
A placeholder vector drawable (e.g., a simple shape or icon).

#### [NEW] [ic_launcher.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml)
Adaptive icon definition for API 26+.

#### [NEW] [ic_launcher_round.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml)
Adaptive round icon definition for API 26+.

#### [NEW] [ic_launcher.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap/ic_launcher.xml)
Fallback icon for older versions (using the same foreground vector or a simple drawable).

## Verification Plan

### Automated Tests
- Run `./gradlew :app:processDebugResources` to verify that resource linking now succeeds.
- Run `./gradlew assembleDebug` to ensure the entire app builds.

### Manual Verification
- Deploy the app to a device or emulator and verify that the launcher icon appears.

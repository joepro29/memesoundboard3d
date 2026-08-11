# Fix Missing Launcher Icon Resources

The project fails to build because `mipmap/ic_launcher` is missing, as referenced in `AndroidManifest.xml`. Both `ic_launcher` and `ic_launcher_round` need to be present for the app to build and display an icon correctly.

## User Review Required

> [!IMPORTANT]
> I will be creating simple placeholder vector icons to fix the build error. If you have specific icon files (PNG/WebP/SVG), you should replace these placeholders with your actual assets later.

## Proposed Changes

### App Resources

I will create a set of adaptive icon resources which is the standard for modern Android apps.

#### [NEW] [ic_launcher_background.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/drawable/ic_launcher_background.xml)
A simple vector drawable for the background of the adaptive icon.

#### [NEW] [ic_launcher_foreground.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/drawable/ic_launcher_foreground.xml)
A simple vector drawable for the foreground (logo) of the adaptive icon.

#### [NEW] [ic_launcher.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml)
The adaptive icon definition for `ic_launcher`.

#### [NEW] [ic_launcher_round.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml)
The adaptive icon definition for `ic_launcher_round`.

#### [NEW] [ic_launcher.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap/ic_launcher.xml)
Legacy launcher icon (vector fallback) for devices below API 26.

#### [NEW] [ic_launcher_round.xml](file:///C:/Users/hamso/Documents/Opencode/meme-soundboard-3d/android-app/app/src/main/res/mipmap/ic_launcher_round.xml)
Legacy round launcher icon (vector fallback) for devices below API 26.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:processDebugResources` to verify that the resource linking error is resolved.
- Run `./gradlew assembleDebug` to ensure the entire app builds correctly.

### Manual Verification
- None required as this is a build fix.

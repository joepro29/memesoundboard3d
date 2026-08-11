# Meme Soundboard 3D - Android App

A native Android app version of the 3D Meme Soundboard with 65+ meme sounds!

## Features

- **65+ Meme Sounds** - All popular meme sounds included
- **3D Card Animations** - Smooth click animations
- **Category Filtering** - Classic, Viral, Gaming, Anime, Funny, Effects
- **Vibration Feedback** - Haptic feedback on sound play
- **Dark Theme** - Neon cyberpunk design
- **Stop Button** - Quickly stop any playing sound

## Requirements

- Android Studio Arctic Fox (2020.3.1) or newer
- Android SDK 24 (Android 7.0 Nougat) or higher
- Kotlin 1.9.0 or higher

## How to Build

### Option 1: Using Android Studio

1. Open Android Studio
2. Click "Open an Existing Project"
3. Navigate to the `android-app` folder
4. Wait for Gradle sync to complete
5. Click the Run button (▶) or press Shift+F10

### Option 2: Using Command Line

```bash
cd android-app
./gradlew assembleDebug
```

The APK will be generated at: `app/build/outputs/apk/debug/app-debug.apk`

## Project Structure

```
android-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/memesoundboard3d/app/
│   │   │   │   ├── MainActivity.kt      # Main activity with sound logic
│   │   │   │   └── SoundAdapter.kt      # RecyclerView adapter
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   └── item_sound.xml
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── tab_background.xml
│   │   │   │   │   ├── tab_background_selected.xml
│   │   │   │   │   └── ic_stop.xml
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml
│   │   │   │       ├── strings.xml
│   │   │   │       └── themes.xml
│   │   │   └── AndroidManifest.xml
│   │   └── ...
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Adding More Sounds

To add more sounds, edit `MainActivity.kt` and add new `Sound` objects to the `setupSounds()` function:

```kotlin
Sound("Sound Name", "https://url-to-sound.mp3", "🎵", "category")
```

## Permissions

- `INTERNET` - To stream sounds from CDN
- `ACCESS_NETWORK_STATE` - Check network availability
- `VIBRATE` - Haptic feedback

## License

This app is for educational purposes. Sounds are sourced from instants.meme.

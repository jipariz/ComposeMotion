# ComposeMotion

This repository contains the sample app created for the STRV engineering blog post on building a collapsing toolbar with Jetpack Compose MotionLayout:

- https://www.strv.com/blog/collapsing-toolbar-using-jetpack-compose-motion-layout-engineering

## What this project shows

The app demonstrates a **collapsing header + scrollable content** pattern implemented with:

- Jetpack Compose UI
- `swipeable` state + nested scroll interop
- `MotionLayout` from `constraintlayout-compose`

As the list scrolls, the header transitions between expanded and collapsed states, animating image size, title position, and text styling.

## Codebase overview

Main implementation files are in:

- `app/src/main/java/com/parez/composemotion/MainActivity.kt`  
  App entry point that renders `CollapsableToolbar()`.
- `app/src/main/java/com/parez/composemotion/CollapsableToolbar.kt`  
  Owns swipe state, nested scroll behavior, and collapse/expand progress.
- `app/src/main/java/com/parez/composemotion/MotionLayoutHeader.kt`  
  Defines start/end `ConstraintSet`s and drives MotionLayout interpolation.
- `app/src/main/java/com/parez/composemotion/ScrollableContent.kt`  
  Renders the scrolling `LazyColumn` content under the header.

Theme files are in:

- `app/src/main/java/com/parez/composemotion/ui/theme`

## Build and run

This is a standard Android Gradle project.

1. Open the project root in Android Studio.
2. Sync Gradle.
3. Run the `app` configuration on an emulator or device.

You can also use Gradle from the root:

```bash
./gradlew assembleDebug
```

## Notes

- The project targets older Android Gradle/Compose versions to match the original article implementation.
- Use the linked blog post for a step-by-step explanation of the design and implementation decisions.

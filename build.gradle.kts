// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // SafeArgs
    id("androidx.navigation.safeargs.kotlin") version "2.7.1" apply false
    // Kapt
    id("org.jetbrains.kotlin.kapt") version "2.0.0-Beta1" apply false
    // Hilty
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}
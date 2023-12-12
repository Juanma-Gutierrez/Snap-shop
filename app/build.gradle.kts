plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // SafeArgs
    id("androidx.navigation.safeargs.kotlin")
    // Parcelice
    id("kotlin-parcelize")
    // Kapt
    id("org.jetbrains.kotlin.kapt")
    // Hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.juanma_gutierrez.snapshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.juanma_gutierrez.snapshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    // ViewBinding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Fragments
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    // Navigation
    val nav_version = "2.7.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    // Retrofit
    val rf_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$rf_version")
    implementation("com.squareup.retrofit2:converter-gson:$rf_version")
    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Room
    val room_version = "2.6.0"
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    // Hilt
    val kapt_hilt_version = "2.48.1"
    kapt("com.google.dagger:hilt-compiler:$kapt_hilt_version")
    implementation("com.google.dagger:hilt-android:$kapt_hilt_version")
    // Toolbar
    implementation("androidx.activity:activity-ktx:1.8.0")
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6")
    // RxJava Observers
    val rx_version = "3.0.0"
    implementation("io.reactivex.rxjava3:rxjava:$rx_version")
    implementation("io.reactivex.rxjava3:rxandroid:$rx_version")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
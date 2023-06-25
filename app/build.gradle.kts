plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

android {
    namespace = "io.itch.fr.quizgame"
    compileSdk = 33

    defaultConfig {
        applicationId = "io.itch.fr.quizgame"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    val composeUiVersion = "1.4.1"
    val composeMaterial3Version = "1.1.0"
    val lifecycleVersion = "2.6.1"
    val navVersion = "2.5.3"
    val accompanistVersion = "0.30.1"
    val roomVersion = "2.5.1"

    // Kotlin extensions
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    // Material
    implementation("androidx.compose.material:material:$composeUiVersion")
    // Material 3
    implementation ("androidx.compose.material3:material3:$composeMaterial3Version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    // Navigating with Compose
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeUiVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeUiVersion")
    // ui util
    implementation("androidx.compose.ui:ui-util:$composeUiVersion")
    // compose foundation
    implementation("androidx.compose.foundation:foundation:$composeUiVersion")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

    // Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    // Preferences DataStore (SharedPreferences like APIs)
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // coil: An image loading library for Android backed by Kotlin Coroutines.
    implementation("io.coil-kt:coil-compose:2.3.0")

    // System UI Controller for Jetpack Compose - Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    // Pager
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")

    // Room Components
    // The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")

    // Gson
    implementation("com.google.code.gson:gson:2.9.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
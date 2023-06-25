buildscript {
//    val compose_ui_version by extra("1.2.0")
    val android_gradle_plugin_version by extra("8.0.2")

}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "${extra["android_gradle_plugin_version"]}" apply false
    id("com.android.library") version "${extra["android_gradle_plugin_version"]}" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false
}
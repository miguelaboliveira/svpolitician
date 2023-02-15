// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.plugin.android)
        classpath(libs.plugin.androidx.navigationSafeArgs)
        classpath(libs.plugin.dagger.hilt)
        classpath(libs.plugin.kotlin)
        classpath(libs.plugin.kotlin.serialization)
        classpath(libs.plugin.kotlinter)
        classpath(libs.plugin.twitter.compose)
        classpath(libs.plugin.square.sqlDelight)
    }
}

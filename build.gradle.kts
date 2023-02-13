// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.plugin.android)
        classpath(libs.plugin.dagger.hilt)
        classpath(libs.plugin.kotlin)
        classpath(libs.plugin.kotlin.serialization)
        classpath(libs.plugin.kotlinter)
        classpath("com.twitter.compose.rules:ktlint:0.0.26")
        classpath(libs.plugin.square.sqlDelight)
    }
}

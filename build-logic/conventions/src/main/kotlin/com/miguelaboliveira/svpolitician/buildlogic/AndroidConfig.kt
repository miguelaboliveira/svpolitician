package com.miguelaboliveira.svpolitician.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Settings and dependencies shared by Android applications and libraries.
 * Call after the Android plugin is applied.
 */
internal fun Project.configureAndroidCommon() {
    extensions.configure<CommonExtension> {
        buildToolsVersion = libs.findVersion("android.buildTools").get().toString()
        compileSdk =
            libs
                .findVersion("android.compileSdk")
                .get()
                .toString()
                .toInt()
        defaultConfig.minSdk =
            libs
                .findVersion("android.minSdk")
                .get()
                .toString()
                .toInt()
        defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        lint.warningsAsErrors = true
        lint.disable += listOf("GradleDependency")
    }

    dependencies {
        add("implementation", libs.findLibrary("androidx.core").get())

        add("testImplementation", libs.findLibrary("junit").get())
        add("testImplementation", libs.findLibrary("kotlin.test").get())
        add("testImplementation", libs.findLibrary("kotlin.testJunit").get())
        add("androidTestImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findLibrary("kotlin.test").get())
        add("androidTestImplementation", libs.findLibrary("kotlin.testJunit").get())
        add("androidTestImplementation", libs.findLibrary("androidx.testExtJunit").get())
        add("androidTestImplementation", libs.findLibrary("androidx.testEspressoCore").get())
    }
}

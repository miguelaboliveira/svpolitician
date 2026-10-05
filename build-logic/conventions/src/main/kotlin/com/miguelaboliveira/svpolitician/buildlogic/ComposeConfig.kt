package com.miguelaboliveira.svpolitician.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Compose setup shared by Android applications and libraries.
 * Call after the Android and Compose compiler plugins are applied.
 */
internal fun Project.configureCompose() {
    extensions.configure<CommonExtension> {
        buildFeatures.compose = true
    }

    dependencies {
        val bom = libs.findLibrary("androidx.composeBom").get()
        add("implementation", platform(bom))
        add("implementation", libs.findLibrary("androidx.composeAnimation").get())
        add("implementation", libs.findLibrary("androidx.composeFoundation").get())
        add("implementation", libs.findLibrary("androidx.composeMaterial3").get())
        add("implementation", libs.findLibrary("androidx.composeRuntime").get())
        add("implementation", libs.findLibrary("androidx.composeUi").get())

        add("implementation", libs.findLibrary("androidx.composeUiToolingPreview").get())
        add("debugImplementation", libs.findLibrary("androidx.composeUiTooling").get())

        add("androidTestImplementation", platform(bom))
        add("androidTestImplementation", libs.findLibrary("androidx.composeUiTestJunit4").get())
        add("debugImplementation", libs.findLibrary("androidx.composeUiTestManifest").get())
    }
}

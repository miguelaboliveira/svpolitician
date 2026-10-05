package com.miguelaboliveira.svpolitician.buildlogic

import dagger.hilt.android.plugin.HiltExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Hilt setup shared by Android applications and libraries.
 * Call after the KSP and Hilt plugins are applied.
 */
internal fun Project.configureHilt() {
    extensions.configure<HiltExtension> {
        enableAggregatingTask = true
    }

    dependencies {
        val compiler = libs.findLibrary("dagger.hiltCompiler").get()
        val testing = libs.findLibrary("dagger.hiltAndroidTesting").get()

        add("implementation", libs.findLibrary("dagger.hiltAndroid").get())
        add("ksp", compiler)

        add("testImplementation", testing)
        add("kspTest", compiler)

        add("androidTestImplementation", testing)
        add("kspAndroidTest", compiler)
    }
}

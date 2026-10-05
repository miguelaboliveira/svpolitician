package com.miguelaboliveira.svpolitician.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.HasConfigurableKotlinCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

/**
 * Shared Kotlin settings. Call after the Kotlin (or Android) plugin is applied,
 * so the `kotlin` extension exists.
 */
internal fun Project.configureKotlin() {
    extensions.configure<KotlinProjectExtension> {
        explicitApi()
        jvmToolchain(21)

        // Both the JVM and Android Kotlin extensions implement this.
        (this as HasConfigurableKotlinCompilerOptions<*>).compilerOptions {
            allWarningsAsErrors.set(true)
        }
    }
}

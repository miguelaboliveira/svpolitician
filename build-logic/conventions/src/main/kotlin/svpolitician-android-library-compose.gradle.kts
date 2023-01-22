plugins {
    id("svpolitician-android-library")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.findVersion("androidx.compose.compiler").get().toString()
        useLiveLiterals = true
    }
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_metrics",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_reports"
        )
    }
}

dependencies {
    implementation(platform(libs.findLibrary("androidx.compose.bom").get()))
    implementation(libs.findLibrary("androidx.composeMaterial3").get())

    implementation(libs.findLibrary("androidx.composeUiToolingPreview").get())
    debugImplementation(libs.findLibrary("androidx.composeUiTooling").get())

    androidTestImplementation(platform(libs.findLibrary("androidx.compose.bom").get()))
    androidTestImplementation(libs.findLibrary("androidx.composeUiTestJunit4").get())
    debugImplementation(libs.findLibrary("androidx.composeUiTestManifest").get())
}

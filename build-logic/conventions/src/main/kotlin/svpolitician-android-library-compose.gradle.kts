import com.miguelaboliveira.svpolitician.buildlogic.libs

plugins {
    id("svpolitician-android-library")
    id("org.jetbrains.kotlin.plugin.compose")
}

composeCompiler {
    metricsDestination = layout.buildDirectory.dir("compose_metrics")
    reportsDestination = layout.buildDirectory.dir("compose_reports")
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.findLibrary("androidx.composeBom").get()))
    implementation(libs.findLibrary("androidx.composeAnimation").get())
    implementation(libs.findLibrary("androidx.composeFoundation").get())
    implementation(libs.findLibrary("androidx.composeMaterial3").get())
    implementation(libs.findLibrary("androidx.composeMaterialIconsCore").get())
    implementation(libs.findLibrary("androidx.composeMaterialIconsExtended").get())
    implementation(libs.findLibrary("androidx.composeRuntime").get())
    implementation(libs.findLibrary("androidx.composeUi").get())

    implementation(libs.findLibrary("androidx.composeUiToolingPreview").get())
    debugImplementation(libs.findLibrary("androidx.composeUiTooling").get())

    androidTestImplementation(platform(libs.findLibrary("androidx.composeBom").get()))
    androidTestImplementation(libs.findLibrary("androidx.composeUiTestJunit4").get())
    debugImplementation(libs.findLibrary("androidx.composeUiTestManifest").get())
}

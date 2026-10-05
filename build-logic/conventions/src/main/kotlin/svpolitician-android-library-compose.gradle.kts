import com.miguelaboliveira.svpolitician.buildlogic.configureCompose
import com.miguelaboliveira.svpolitician.buildlogic.libs

plugins {
    id("svpolitician-android-library")
    id("org.jetbrains.kotlin.plugin.compose")
}

configureCompose()

composeCompiler {
    metricsDestination = layout.buildDirectory.dir("compose_metrics")
    reportsDestination = layout.buildDirectory.dir("compose_reports")
}

dependencies {
    implementation(libs.findLibrary("androidx.composeMaterialIconsCore").get())
    implementation(libs.findLibrary("androidx.composeMaterialIconsExtended").get())
}

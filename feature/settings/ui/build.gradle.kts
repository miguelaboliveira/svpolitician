plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.settings.ui"
    resourcePrefix = "settings"
}

dependencies {
    implementation(projects.feature.settings.domain)
    implementation(projects.ui.preview)
    implementation(projects.ui.theme)
    implementation(projects.ui.fragmentext)
    kapt(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}
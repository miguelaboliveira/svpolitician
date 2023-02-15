plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.home.ui"
}

dependencies {
    implementation(projects.feature.home.domain)
    implementation(projects.ui.preview)
    implementation(projects.ui.theme)
    kapt(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}

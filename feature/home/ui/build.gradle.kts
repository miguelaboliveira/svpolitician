plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.home.ui"
    resourcePrefix = "home"
}

dependencies {
    implementation(projects.feature.home.domain)
    implementation(projects.ui.preview)
    implementation(projects.ui.theme)
    implementation(projects.ui.fragmentext)
    kapt(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}

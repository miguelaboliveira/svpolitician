plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.history.ui"
    resourcePrefix = "history"
}

dependencies {
    implementation(projects.feature.history.domain)
    implementation(projects.ui.preview)
    implementation(projects.ui.theme)
    implementation(projects.ui.fragmentext)
    kapt(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    implementation(libs.kotlinx.collectionsImmutable)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}

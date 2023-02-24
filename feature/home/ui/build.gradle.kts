plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.home.ui"
    resourcePrefix = "home"
}

dependencies {
    implementation(projects.feature.home.domain)
    implementation(projects.core.ui.design)
    implementation(projects.core.ui.fragmentext)
    implementation(projects.core.ui.composeext)
    implementation(projects.core.ui.error)
    kapt(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}

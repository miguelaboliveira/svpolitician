plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.settings.ui"
    resourcePrefix = "settings"
}

dependencies {
    implementation(projects.feature.settings.domain)
    implementation(projects.core.ui.design)
    implementation(projects.core.ui.fragmentext)
    ksp(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}

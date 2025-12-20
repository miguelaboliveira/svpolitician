plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.history.ui"
    resourcePrefix = "history"
}

dependencies {
    implementation(projects.feature.history.domain)
    implementation(projects.core.ui.design)
    implementation(projects.core.ui.fragmentext)
    ksp(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    implementation(libs.kotlinx.collectionsImmutable)
    testImplementation(libs.androidx.lifecycleRuntimeTesting)
}

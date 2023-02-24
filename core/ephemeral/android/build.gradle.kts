plugins {
    id("svpolitician-android-library")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.core.ephemeral.android"
}

dependencies {
    api(projects.core.ephemeral.core)
    implementation(libs.androidx.lifecycleViewmodelSavedstate)
    implementation(libs.kotlinx.serializationJson)
}

plugins {
    id("svpolitician-android-library")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.data.ephemeral.android"
}

dependencies {
    api(projects.data.ephemeral.core)
    implementation(libs.androidx.lifecycleViewmodelSavedstate)
    implementation(libs.kotlinx.serializationJson)
}

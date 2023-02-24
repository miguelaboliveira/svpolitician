plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.core.network.impl"
}

dependencies {
    api(projects.core.network.public)
    implementation(libs.bundles.square.retrofit2)
    implementation(libs.bundles.square.okhttp)
    implementation(libs.kotlinx.serializationJson)
}

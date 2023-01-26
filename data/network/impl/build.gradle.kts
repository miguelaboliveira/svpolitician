plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.data.network.impl"
}

dependencies {
    api(projects.data.network.public)
    implementation(libs.bundles.square.retrofit2)
    implementation(libs.bundles.square.okhttp)
    implementation(libs.kotlinx.serializationJson)
}

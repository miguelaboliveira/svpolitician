plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.core.userpreferences.impl"
}

dependencies {
    api(projects.core.userpreferences.public)
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serializationJson)
}

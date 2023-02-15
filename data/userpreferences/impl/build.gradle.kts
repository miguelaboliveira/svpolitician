plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.data.userpreferences.impl"
}

dependencies {
    api(projects.data.userpreferences.public)
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serializationJson)
}

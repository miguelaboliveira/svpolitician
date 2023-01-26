plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.data.database.impl"
}

dependencies {
    api(projects.data.database.public)
    implementation(libs.square.sqlDelightAndroidDriver)
}

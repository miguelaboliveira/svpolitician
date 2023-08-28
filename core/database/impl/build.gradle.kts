plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.core.database.impl"
}

dependencies {
    api(projects.core.database.public)
    implementation(libs.cash.sqlDelightAndroidDriver)
}

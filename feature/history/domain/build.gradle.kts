plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.history.domain"
}

dependencies {
    api(projects.data.database.public)
    implementation(libs.square.sqlDelightCoroutinesExt)
}

plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.home.domain"
}

dependencies {
    implementation(projects.data.database.public)
    implementation(projects.data.network.public)
    implementation(libs.square.sqlDelightCoroutinesExt)
}

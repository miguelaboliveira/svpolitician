plugins {
    id("svpolitician-android-library-compose")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.ui.fragmentext"
}

dependencies {
    implementation(projects.ui.theme)
    implementation(libs.androidx.fragment)
}

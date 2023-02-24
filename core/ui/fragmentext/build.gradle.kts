plugins {
    id("svpolitician-android-library-compose")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.core.ui.fragmentext"
}

dependencies {
    implementation(projects.core.ui.design)
    implementation(libs.androidx.fragment)
}

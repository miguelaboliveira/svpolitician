plugins {
    id("svpolitician-android-library-hilt")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.settings.domain"
}

dependencies {
    api(projects.data.userpreferences.public)
}

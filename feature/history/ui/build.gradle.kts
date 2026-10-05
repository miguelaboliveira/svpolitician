plugins {
    id("svpolitician-android-library-feature")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.feature.history.ui"
    resourcePrefix = "history"
}

dependencies {
    implementation(projects.feature.history.domain)
    implementation(projects.core.ui.design)
    implementation(projects.core.ui.fragmentext)
    implementation(libs.kotlinx.collectionsImmutable)
}

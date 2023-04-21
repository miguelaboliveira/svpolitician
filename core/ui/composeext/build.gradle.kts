plugins {
    id("svpolitician-android-library-compose")
}

android {
    namespace = "com.miguelaboliveira.svpolitician.core.ui.composeext"
    resourcePrefix = "composeext"

    buildFeatures {
        buildConfig = true
    }
}

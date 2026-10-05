plugins {
    id("svpolitician-android-application")
}

android {
    namespace = "com.miguelaboliveira.svpolitician"
    defaultConfig {
        applicationId = "com.miguelaboliveira.svpolitician"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.ui.design)

    implementation(projects.feature.home.ui)
    implementation(projects.feature.history.ui)
    implementation(projects.feature.settings.ui)

    // Database
    implementation(projects.core.database.impl)
    testImplementation(projects.core.database.fake)

    // Network
    implementation(projects.core.network.impl)
    testImplementation(projects.core.network.fake)

    // UserPreferences
    implementation(projects.core.userpreferences.impl)
    testImplementation(projects.core.userpreferences.fake)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.viewPager2)

    // Leak
    debugImplementation(libs.square.leakcanaryAndroid)
}

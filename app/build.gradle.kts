plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.dagger.hilt.get().pluginId)
    id(libs.plugins.kotlinter.get().pluginId)
}

kotlin {
    explicitApi()
    jvmToolchain(17)
}

android {
    namespace = "com.miguelaboliveira.svpolitician"
    buildToolsVersion = libs.versions.android.buildTools.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.miguelaboliveira.svpolitician"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    kotlinOptions {
        allWarningsAsErrors = true
    }
    lint {
        warningsAsErrors = true
        disable += listOf("GradleDependency")
        baseline = file("lint-baseline.xml")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true // Required by hilt
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(projects.core.ui.design)

    implementation(projects.feature.home.ui)
    implementation(projects.feature.history.ui)
    implementation(projects.feature.settings.ui)

    // Compose
    implementation(platform(libs.androidx.composeBom))
    implementation(libs.androidx.composeAnimation)
    implementation(libs.androidx.composeFoundation)
    implementation(libs.androidx.composeMaterial3)
    implementation(libs.androidx.composeRuntime)
    implementation(libs.androidx.composeUi)

    implementation(libs.androidx.composeUiToolingPreview)
    debugImplementation(libs.androidx.composeUiTooling)

    androidTestImplementation(platform(libs.androidx.composeBom))
    androidTestImplementation(libs.androidx.composeUiTestJunit4)
    debugImplementation(libs.androidx.composeUiTestManifest)

    // Hilt
    implementation(libs.dagger.hiltAndroid)
    kapt(libs.dagger.hiltCompiler)

    androidTestImplementation(libs.dagger.hiltAndroidTesting)
    kaptAndroidTest(libs.dagger.hiltCompiler)

    testImplementation(libs.dagger.hiltAndroidTesting)
    kaptTest(libs.dagger.hiltCompiler)

    // Database
    implementation(projects.core.database.impl)
    testImplementation(projects.core.database.fake)

    // Network
    implementation(projects.core.network.impl)
    testImplementation(projects.core.network.fake)

    // UserPreferences
    implementation(projects.core.userpreferences.impl)
    testImplementation(projects.core.userpreferences.fake)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.viewPager2)

    // Leak
    debugImplementation(libs.square.leakcanaryAndroid)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.testJunit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.testJunit)
    androidTestImplementation(libs.androidx.testExtJunit)
    androidTestImplementation(libs.androidx.testEspressoCore)
}

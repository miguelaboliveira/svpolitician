plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.dagger.hilt.get().pluginId)
    id(libs.plugins.kotlinter.get().pluginId)
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

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        allWarningsAsErrors = true
    }
    lint {
        warningsAsErrors = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    packagingOptions {
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
    implementation(projects.ui.theme)

    // Compose
    implementation(platform(libs.androidx.composeBom))
    implementation(libs.androidx.composeMaterial3)

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

    implementation(libs.androidx.core)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
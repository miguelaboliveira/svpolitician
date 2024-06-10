plugins {
    id("svpolitician-android-library")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

kapt {
    correctErrorTypes = true // Required by hilt
}

android {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xsuppress-version-warnings")
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(libs.findLibrary("dagger.hiltAndroid").get())
    kapt(libs.findLibrary("dagger.hiltCompiler").get())

    testImplementation(libs.findLibrary("dagger.hiltAndroidTesting").get())
    kaptTest(libs.findLibrary("dagger.hiltCompiler").get())

    androidTestImplementation(libs.findLibrary("dagger.hiltAndroidTesting").get())
    kaptAndroidTest(libs.findLibrary("dagger.hiltCompiler").get())
}

plugins {
    id("svpolitician-android-library")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(libs.findLibrary("dagger.hiltAndroid").get())
    ksp(libs.findLibrary("dagger.hiltCompiler").get())

    testImplementation(libs.findLibrary("dagger.hiltAndroidTesting").get())
    kspTest(libs.findLibrary("dagger.hiltCompiler").get())

    androidTestImplementation(libs.findLibrary("dagger.hiltAndroidTesting").get())
    kspAndroidTest(libs.findLibrary("dagger.hiltCompiler").get())
}

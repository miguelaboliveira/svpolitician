plugins {
    id("svpolitician-android-library-compose")
    id("svpolitician-android-library-hilt")
    id("androidx.navigation.safeargs")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    implementation(libs.findLibrary("androidx.navigation").get())
    implementation(libs.findLibrary("androidx.hiltNavigationFragment").get())

    androidTestImplementation(libs.findLibrary("androidx.navigationTesting").get())
}

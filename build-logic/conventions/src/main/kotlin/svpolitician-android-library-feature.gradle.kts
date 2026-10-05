import com.miguelaboliveira.svpolitician.buildlogic.libs

plugins {
    id("svpolitician-android-library-compose")
    id("svpolitician-android-library-hilt")
    id("androidx.navigation.safeargs")
}

dependencies {
    implementation(libs.findLibrary("androidx.fragment").get())
    implementation(libs.findLibrary("androidx.navigation").get())
    implementation(libs.findLibrary("androidx.hiltNavigationFragment").get())

    androidTestImplementation(libs.findLibrary("androidx.navigationTesting").get())
}

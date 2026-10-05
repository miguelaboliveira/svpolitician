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
    implementation(libs.findLibrary("androidx.lifecycleRuntimeCompose").get())
    ksp(libs.findLibrary("androidx.lifecycleCompiler").get())

    testImplementation(libs.findLibrary("androidx.lifecycleRuntimeTesting").get())
    androidTestImplementation(libs.findLibrary("androidx.navigationTesting").get())
}

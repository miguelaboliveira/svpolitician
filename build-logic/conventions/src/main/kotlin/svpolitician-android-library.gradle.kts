plugins {
    id("com.android.library")
    id("org.jmailen.kotlinter")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    explicitApi()
    jvmToolchain(21)

    compilerOptions {
        allWarningsAsErrors = true
    }
}

android {
    buildToolsVersion = libs.findVersion("android.buildTools").get().toString()
    compileSdk =
        libs
            .findVersion("android.compileSdk")
            .get()
            .toString()
            .toInt()
    defaultConfig {
        minSdk =
            libs
                .findVersion("android.minSdk")
                .get()
                .toString()
                .toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        warningsAsErrors = true
        disable += listOf("GradleDependency")
    }
}

dependencies {
    implementation(libs.findLibrary("androidx.core").get())
    implementation(libs.findLibrary("kotlinx.coroutinesAndroid").get())
    testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
    androidTestImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())

    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("kotlin.test").get())
    testImplementation(libs.findLibrary("kotlin.testJunit").get())
    androidTestImplementation(libs.findLibrary("junit").get())
    androidTestImplementation(libs.findLibrary("kotlin.test").get())
    androidTestImplementation(libs.findLibrary("kotlin.testJunit").get())
    androidTestImplementation(libs.findLibrary("androidx.testExtJunit").get())
    androidTestImplementation(libs.findLibrary("androidx.testEspressoCore").get())
}

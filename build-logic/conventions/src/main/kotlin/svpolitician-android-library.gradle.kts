plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jmailen.kotlinter")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    buildToolsVersion = libs.findVersion("android.buildTools").get().toString()
    compileSdk = libs.findVersion("android.compileSdk").get().toString().toInt()
    defaultConfig {
        minSdk = libs.findVersion("android.minSdk").get().toString().toInt()
        targetSdk = libs.findVersion("android.targetSdk").get().toString().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        allWarningsAsErrors = true
        freeCompilerArgs += "-Xexplicit-api=strict" // https://youtrack.jetbrains.com/issue/KT-37652
    }
    lint {
        warningsAsErrors = true
    }
}

kotlin {
    explicitApi() // Has no effect. See https://youtrack.jetbrains.com/issue/KT-37652
    jvmToolchain(11)
}

dependencies {
    implementation(libs.findLibrary("androidx.core").get())
    implementation(libs.findLibrary("kotlinx.coroutinesAndroid").get())
    testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
    androidTestImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
}
plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jmailen.kotlinter")
}

kotlin {
    explicitApi()
    jvmToolchain(17)
}

tasks.compileKotlin {
    compilerOptions {
        allWarningsAsErrors.set(true)
    }
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
dependencies {
    implementation(libs.findLibrary("kotlinx.coroutinesCore").get())
    testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("kotlin.test").get())
    testImplementation(libs.findLibrary("kotlin.testJunit").get())
}

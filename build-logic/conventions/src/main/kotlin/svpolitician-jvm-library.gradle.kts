plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    explicitApi()
    jvmToolchain(11)
}

tasks.compileKotlin {
    compilerOptions {
        allWarningsAsErrors.set(true)
    }
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
dependencies {
    implementation(libs.findLibrary("kotlinx.coroutines").get())
    testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
}

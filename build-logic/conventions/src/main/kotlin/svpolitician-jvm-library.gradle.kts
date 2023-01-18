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
    // Add common dependencies for all jvm libraries
}

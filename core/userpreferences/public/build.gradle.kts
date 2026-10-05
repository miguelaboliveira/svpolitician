plugins {
    id("svpolitician-jvm-library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(libs.kotlinx.serializationCore)
}

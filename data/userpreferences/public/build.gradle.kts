plugins {
    id("svpolitician-jvm-library")
    id(libs.plugins.kotlin.serialization.get().pluginId)
}

dependencies {
    implementation(libs.kotlinx.serializationCore)
}

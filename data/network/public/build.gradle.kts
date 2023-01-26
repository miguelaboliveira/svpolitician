plugins {
    id("svpolitician-jvm-library")
    id(libs.plugins.kotlin.serialization.get().pluginId)
}

dependencies {
    implementation(libs.square.retrofit2)
    implementation(libs.kotlinx.serializationCore)
}

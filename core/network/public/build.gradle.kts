plugins {
    id("svpolitician-jvm-library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(libs.square.retrofit2)
    implementation(libs.kotlinx.serializationCore)
}

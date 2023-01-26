plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.data.network.public)
    implementation(libs.bundles.square.retrofit2)
    implementation(libs.kotlinx.serializationJson)
}

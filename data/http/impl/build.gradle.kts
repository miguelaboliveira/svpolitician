plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.data.http.public)
    implementation(libs.bundles.square.retrofit)
    implementation(libs.kotlinx.serialization.json)
}

plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.data.database.public)
    implementation(projects.data.network.public)
    implementation(libs.javax.inject)
    implementation(libs.square.sqlDelightCoroutinesExt)
}

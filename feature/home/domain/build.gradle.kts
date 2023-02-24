plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.core.database.public)
    implementation(projects.core.network.public)
    implementation(libs.javax.inject)
    implementation(libs.square.sqlDelightCoroutinesExt)
}

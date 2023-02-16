plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.data.database.public)
    implementation(libs.javax.inject)
    implementation(libs.square.sqlDelightCoroutinesExt)
}

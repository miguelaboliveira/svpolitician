plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.data.database.public)
    implementation(libs.square.sqlDelightSqliteDriver)
}

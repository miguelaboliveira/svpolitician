plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    api(projects.core.database.public)
    implementation(libs.square.sqlDelightSqliteDriver)
}

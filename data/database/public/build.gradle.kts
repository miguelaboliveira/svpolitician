plugins {
    id("svpolitician-jvm-library")
    id(libs.plugins.square.sqlDelight.get().pluginId)
}

sqldelight {
    database("SVPoliticianDatabase") {
        packageName = "com.miguelaboliveira.svpolitician.data.database"
        schemaOutputDirectory = file("src/main/sqldelight/schema")
    }
}
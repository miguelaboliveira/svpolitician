plugins {
    id("svpolitician-jvm-library")
    id(libs.plugins.square.sqlDelight.get().pluginId)
}

sqldelight {
    database("SVPoliticianDatabase") {
        packageName = "com.miguelaboliveira.svpolitician.core.database"
        schemaOutputDirectory = file("src/main/sqldelight/schema")
    }
}

tasks.lintKotlinMain {
    exclude(
        "/com/miguelaboliveira/svpolitician/core/database/**"
    )
}
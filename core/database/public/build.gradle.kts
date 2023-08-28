plugins {
    id("svpolitician-jvm-library")
    id(libs.plugins.cash.sqlDelight.get().pluginId)
}

sqldelight {
    databases {
        create("SVPoliticianDatabase") {
            packageName.set("com.miguelaboliveira.svpolitician.core.database")
            schemaOutputDirectory.set(file("src/main/sqldelight/schema"))
        }
    }
}

tasks.lintKotlinMain {
    exclude(
        "/com/miguelaboliveira/svpolitician/core/database/**"
    )
}

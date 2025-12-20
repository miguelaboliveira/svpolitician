import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    id("svpolitician-jvm-library")
    id(
        libs.plugins.cash.sqlDelight
            .get()
            .pluginId,
    )
}

sqldelight {
    databases {
        create("SVPoliticianDatabase") {
            packageName.set("com.miguelaboliveira.svpolitician.core.database")
            schemaOutputDirectory.set(file("src/main/sqldelight/schema"))
        }
    }
}

tasks.withType<LintTask> {
    exclude { it.file.path.contains("/generated") }
}

tasks.withType<FormatTask> {
    exclude { it.file.path.contains("/generated") }
}

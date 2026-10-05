import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    id("svpolitician-jvm-library")
    id("app.cash.sqldelight")
}

sqldelight {
    databases {
        create("SVPoliticianDatabase") {
            packageName.set("com.miguelaboliveira.svpolitician.core.database")
            schemaOutputDirectory.set(file("src/main/sqldelight/schema"))
        }
    }
}

dependencies {
    api(libs.cash.sqlDelightRuntime)
}

tasks.withType<LintTask>().configureEach {
    exclude { it.file.path.contains("/generated") }
}

tasks.withType<FormatTask>().configureEach {
    exclude { it.file.path.contains("/generated") }
}

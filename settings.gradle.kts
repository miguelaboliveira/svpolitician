enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Locations of Gradle Plugins
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    includeBuild("build-logic")
}

// Locations of libraries
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Gradle dependencies --scan
plugins {
    id("com.gradle.develocity") version "3.18"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

// Project name
rootProject.name = "SVPolitician"

// Subprojects
include("app")
include("core:database:public")
include("core:database:impl")
include("core:database:fake")
include("core:ephemeral:core")
include("core:ephemeral:android")
include("core:network:public")
include("core:network:impl")
include("core:network:fake")
include("core:ui:design")
include("core:ui:fragmentext")
include("core:ui:composeext")
include("core:ui:error")
include("core:userpreferences:public")
include("core:userpreferences:impl")
include("core:userpreferences:fake")
include("feature:home:domain")
include("feature:home:ui")
include("feature:history:domain")
include("feature:history:ui")
include("feature:settings:domain")
include("feature:settings:ui")

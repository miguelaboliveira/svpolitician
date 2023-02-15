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

// Project name
rootProject.name = "SVPolitician"

// Subprojects
include("app")
include("data:database:public")
include("data:database:impl")
include("data:database:fake")
include("data:ephemeral:core")
include("data:ephemeral:android")
include("data:network:public")
include("data:network:impl")
include("data:network:fake")
include("domain:presenter")
include("feature:home:domain")
include("feature:home:ui")
include("feature:history:domain")
include("feature:history:ui")
include("ui:theme")
include("ui:preview")

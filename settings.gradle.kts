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
include("data:network:public")
include("data:network:impl")
include("data:network:fake")
include("data:database:public")
include("data:database:impl")
include("data:database:fake")
include("domain:presenter")
include("ui:theme")
include("ui:preview")
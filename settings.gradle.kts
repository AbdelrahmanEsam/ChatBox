pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("myLibs") {
            from(files("/gradle/libs.versions.toml"))
        }
    }



    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ChatBox"
include (":app")
include(":feature:auth")
include(":feature:auth:presentation")
include(":feature:auth:domain")
include(":feature:auth:data")
include(":feature")
include(":core:designsystem")
include(":feature:chat")
include(":feature:chat:data")
include(":feature:chat:domain")
include(":feature:chat:presentation")
include(":core:common")

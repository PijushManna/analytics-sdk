pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("org.jetbrains.kotlin.jvm") version "2.3.0"
        id("org.jetbrains.kotlin.android") version "2.3.0"
        id("com.android.library") version "8.13.2"
    }
}

rootProject.name = "analytics-sdk"

include(":analytics-api")
include(":analytics-core")
include(":analytics-store")
include(":analytics-worker")
include(":analytics-firebase")
include(":analytics-noop")
include(":analytics-bom")

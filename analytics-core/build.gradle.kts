plugins {
    alias(libs.plugins.kotlin.jvm)
    id("maven-publish")
}


dependencies {
    api(project(":analytics-api"))
    compileOnly(libs.kotlinx.coroutines.core)
}
kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
        }
    }
}

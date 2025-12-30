plugins {
    alias(libs.plugins.kotlin.jvm)
    id("maven-publish")
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
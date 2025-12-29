plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
        }
    }
}
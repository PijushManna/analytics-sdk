plugins {
    id("org.jetbrains.kotlin.jvm")
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
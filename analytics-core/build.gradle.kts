plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}


dependencies {
    implementation(project(":analytics-api"))
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
        }
    }
}
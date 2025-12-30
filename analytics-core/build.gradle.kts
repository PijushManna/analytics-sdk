plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}


dependencies {
    implementation(project(":analytics-api"))
    //noinspection UseTomlInstead
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
        }
    }
}
plugins {
    alias(libs.plugins.android.library) apply false
}

allprojects {
    group = "com.github.codew1997"
    version = "1.0.0"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}
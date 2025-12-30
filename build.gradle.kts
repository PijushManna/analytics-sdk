plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.kapt) apply false
}

allprojects {
    group = "com.github.codew1997"
    version = "1.0.4"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}
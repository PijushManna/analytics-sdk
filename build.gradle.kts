plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.kapt) apply false
}

allprojects {
    group = "com.github.pijushmanna.analytics-sdk"
    version = "1.0.0"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.analytics.worker"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}
dependencies {
    implementation(project(":analytics-store"))
    implementation(project(":analytics-api"))
    compileOnly(libs.androidx.work.runtime.ktx)
}
kotlin {
    jvmToolchain(17)
}
publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}


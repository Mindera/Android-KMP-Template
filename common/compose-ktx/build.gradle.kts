plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.compose.extensions"
version = "1.0.0"

kotlin {
    androidTarget()
    jvm("desktop")
    iOS {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }
    }
}

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.mindera.launches.data.remote.mindera.rest.schema"
version = "1.0.0"

kotlin {
    jvm()
    iOS {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization)
            }
        }
    }
}

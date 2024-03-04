plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
}

group = "com.mindera.launches.data.remote.mindera.rest.sample"
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
        val androidMain by getting {
            dependencies {}
        }

        val commonMain by getting {
            dependencies {

            }
        }

        if (iOSEnabled) {
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting

            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
        }
    }
}

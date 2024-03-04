plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.mindera.rest.client.ktor"
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
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(projects.common.api.mindera.rest.ktor.interceptors)
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
                dependencies {
                    implementation(libs.ktor.darwin)
                }
            }
        }
    }
}

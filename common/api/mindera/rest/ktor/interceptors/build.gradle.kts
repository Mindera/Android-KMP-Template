plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.mindera.rest.client.ktor.interceptors"
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
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.kotlinx.serialization)
                implementation(projects.common.domain)
            }
        }
    }
}

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "com.mindera.launches.di"
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
                implementation(projects.features.launches.presentation)
                implementation(projects.features.launches.domain)
                implementation(projects.features.launches.data)
                implementation(projects.features.launches.data.remote.mindera.rest.ktor)
                implementation(projects.common.api.mindera.rest.ktor)

                implementation(libs.ktor.logging)
                implementation(libs.ktor.okhttp)
                implementation(libs.koin.core)
                implementation(libs.ktor.core)
                implementation(libs.ktor.content.negotiation)

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

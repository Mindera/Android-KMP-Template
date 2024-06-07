plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

group = "com.mindera.currencyexchange.database"
version = "1.0.0"

kotlin {
    androidTarget()
    jvm()
    iOS {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.experimental.ExperimentalObjCRefinement")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.core)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.kotlinx.serialization)
                implementation(projects.features.currencyexchange.domain)
                implementation(libs.runtime)
                implementation(
                    projects.features.currencyexchange.data.remote.mindera.rest.schema
                )
                implementation(
                    projects.features.currencyexchange.data.remote.mindera.rest.sample
                )
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.android.driver)
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
                    implementation(libs.native.driver)
                }
            }
        }
    }

    sqldelight {
        databases {
            create("AppDatabase") {
                packageName.set("com.mindera.currencyexchange.database")
            }
        }
    }
}

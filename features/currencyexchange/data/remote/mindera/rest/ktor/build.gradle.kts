plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.mindera.currencyexchange.data.remote.mindera.rest.ktor"
version = "1.0.0"

kotlin {
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
                implementation(projects.features.currencyexchange.data.remote.mindera.rest.schema)
                implementation(projects.features.currencyexchange.domain)
                implementation(projects.features.currencyexchange.database)

                // sample
                implementation(projects.features.currencyexchange.data.remote.mindera.rest.sample)
            }
        }
    }
}

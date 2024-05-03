plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.mindera.kmpexample.currencyexchange.data"
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
        all {
            languageSettings {
                optIn("kotlin.experimental.ExperimentalObjCRefinement")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(projects.common.coroutinesKtx)
                implementation(projects.features.currencyexchange.domain)
                implementation(projects.features.currencyexchange.data.remote.mindera.rest.ktor)
                implementation(projects.common.domain)
            }
        }
    }
}

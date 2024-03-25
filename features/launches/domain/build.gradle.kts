plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "com.mindera.kmpexample.launches.domain"
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
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)
                implementation(projects.common.coroutinesKtx)
                implementation(projects.common.domain)           }
        }
    }
}

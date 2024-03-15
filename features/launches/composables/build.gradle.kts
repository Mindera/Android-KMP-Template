plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.kmpexample.launches.composables"
version = "1.0.0"

kotlin {
    androidTarget()
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(projects.common.composeKtx)
                implementation(projects.features.launches.domain)
            }
        }
    }
}

android {
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
}

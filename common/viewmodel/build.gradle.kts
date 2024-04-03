plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.lifecycle.viewmodel"
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
        val commonMain by getting {
            dependencies {
//                api(libs.precompose.viewmodel)
                implementation(compose.runtime)
                implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.0-alpha03")
            }
        }
    }
}

@file:Suppress(
    "DSL_SCOPE_VIOLATION", // TODO: Remove once KTIJ-19369 is fixed. https://youtrack.jetbrains.com/issue/KTIJ-19369
)

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.mindera.precompose"
version = "1.0.0"

kotlin {
//    jvm()
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
//                implementation(compose.foundation)
//                implementation(compose.material3)
//                implementation(libs.kotlinx.datetime)
//                implementation(libs.precompose)

//                implementation(libs.precompose.viewmodel)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.kotlinx.datetime)
                implementation(libs.precompose)
            }
        }
    }
}

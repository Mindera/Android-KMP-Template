plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.kmpexample.navigation"
version = "1.0.0"

kotlin {
    androidTarget()
    jvm("desktop")

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.animation)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.precompose)
                implementation(projects.features.launches.presentation)
                implementation(projects.libraries.precompose)
            }
        }
    }
}

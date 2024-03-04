@file:Suppress(
    "DSL_SCOPE_VIOLATION", // TODO: Remove once KTIJ-19369 is fixed. https://youtrack.jetbrains.com/issue/KTIJ-19369
)

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.precompose"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.kotlinx.datetime)
                implementation(libs.precompose)
                implementation(libs.precompose.viewmodel)
            }
        }
    }
}

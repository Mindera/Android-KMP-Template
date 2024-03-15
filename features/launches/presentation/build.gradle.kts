plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.kmpexample.features.launches.presentation"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(projects.common.domain)
                implementation(projects.common.coroutinesKtx)
                implementation(projects.common.composeKtx)
                implementation(projects.common.viewmodel)
                implementation(projects.features.launches.domain)
                implementation(projects.features.launches.composables)
                implementation(projects.libraries.precompose)

                // These dependencies should not be here
                // once proper dependency injection is in place
                // these can be removed
                implementation(projects.features.launches.data)
                implementation(projects.features.launches.data.remote.mindera.rest.ktor)
                implementation(projects.common.api.mindera.rest.ktor)
                implementation(libs.ktor.core)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.kotlinx.serialization)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.okhttp)
                implementation(libs.koin.core)
            }
        }
    }
}

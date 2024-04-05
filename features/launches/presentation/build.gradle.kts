plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.mindera.kmpexample.features.launches.presentation"
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
                implementation(libs.koin.core)

            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(libs.lifecycle.viewmodel.ktx)
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
                    implementation(libs.lifecycle.viewmodel)
                }
            }
        }
    }

}

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "com.mindera.coroutines"
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
                implementation(libs.kotlinx.datetime)
                api(libs.kotlinx.coroutines.core)
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
            }
        }
    }
}

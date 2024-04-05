import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.mindera.kmpexample"
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            kotlin.srcDir("$buildDir/generated/ksp/jvm/jvmMain/java")
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.precompose)
                implementation(projects.app.navigation)
                implementation(libs.koin.core)
                implementation(projects.features.launches.di)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(Dmg, Msi, Deb)
            packageName = "CurrencyExchange"
            packageVersion = "1.0.0"
            version = "1.0-SNAPSHOT"
            description = "CurrencyExchange"
            copyright = "© 2023 Mindera. No rights reserved."
            vendor = "NoIdea"
            val iconsRoot = project.file("src/jvmMain/resources")
            linux {
                iconFile.set(File(iconsRoot, "ic_launcher_round.webp"))
            }
            windows {
                menuGroup = "CurrencyExchange"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "26181E8D-ADC6-4D03-BC67-B642F461AED4"
                iconFile.set(iconsRoot.resolve("ic_launcher.png"))
            }
            macOS {
                iconFile.set(File(iconsRoot, "ic_launcher_round.webp"))
                dockName = "dockName"
                setDockNameSameAsPackageName = true
//                var appStore: Boolean = false
                appCategory = "appCategory"
                pkgPackageBuildVersion = "pkgPackageBuildVersion"
            }
        }
    }
}

dependencies {
    add("kspJvm", libs.google.dagger.compiler)
}

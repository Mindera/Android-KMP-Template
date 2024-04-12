import org.gradle.api.internal.catalog.DelegatingProjectDependency
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

val libName = "CurrencyExchangeKMP"
val repo = "https://github.com/Mindera/Android-KMP-Template"
group = "com.mindera.kmpexample"
version = "1.0.0"

fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.framework(
    name: String,
    configure: org.jetbrains.kotlin.gradle.plugin.mpp.Framework.() -> Unit,
) {
    val xcFramework = XCFramework(name)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework(name) {
            freeCompilerArgs += listOf(
                "-Xexport-kdoc",
            )
            xcFramework.add(this)
            configure()
        }
    }
}

fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.framework(
    name: String,
    modules: List<DelegatingProjectDependency>,
    configure: org.jetbrains.kotlin.gradle.plugin.mpp.Framework.() -> Unit,
) {
    iOS(enabled = {
        framework(name) {
            configure()
            modules.forEach(::export)
        }
    }, disabled = { jvm() })

    sourceSets {
        commonMain {
            dependencies {
                modules.forEach(::api)
            }
        }
    }
}

private val modules: List<DelegatingProjectDependency> = listOf(
    projects.features.launches.data,
    projects.features.launches.data.remote.mindera.rest.ktor,
    projects.features.launches.domain,
    projects.common.api.mindera.rest.ktor,
    projects.common.api.mindera.rest.ktor.interceptors,
    projects.common.coroutinesKtx,
    projects.common.domain,
    projects.features.launches.di,
    projects.features.launches.presentation,
)

kotlin {
    framework(libName, modules) {
//        linkerOpts.add("-lsqlite3")
    }
    tasks {
        registerPublish(libName, type = "Debug", repo, branch = "iOS_Framework")
        registerPublish(libName, type = "Release", repo, branch = "iOS_Framework")
    }
}

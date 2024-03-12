@file:Suppress(
    "DSL_SCOPE_VIOLATION", // TODO: Remove once KTIJ-19369 is fixed. https://youtrack.jetbrains.com/issue/KTIJ-19369
    "SuspiciousCollectionReassignment",
    "UnstableApiUsage",
)

import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.api.AndroidBasePlugin
import com.google.devtools.ksp.gradle.KspExtension
import com.google.devtools.ksp.gradle.KspGradleSubplugin
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.jvm.toolchain.JavaLanguageVersion.of
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.android.build.gradle.BaseExtension as AndroidBaseExtension

plugins {
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.compose.multiplatform).apply(false)
//    alias(libs.plugins.sqldelight).apply(false)
//    alias(libs.plugins.apollo).apply(false)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp).apply(false)
}

buildscript {
    dependencies {
//        classpath(libs.sqldelight.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// Baseline configuration
subprojects {
    plugins.withType<JavaBasePlugin> {
        configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(of(17))
            }
        }
    }
    plugins.withType<AndroidBasePlugin> {
        configure<AndroidBaseExtension> {
            compileSdkVersion(34)
            defaultConfig {
                minSdk = 26
                targetSdk = 33
            }
            compileOptions {
                // https://developer.android.com/studio/write/java8-support
                isCoreLibraryDesugaringEnabled = true
            }
        }

        dependencies {
            // https://developer.android.com/studio/write/java8-support
            "coreLibraryDesugaring"(libs.android.desugar)
        }
    }
}

// Add Detekt on all subprojects
subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        buildUponDefaultConfig = true // preconfigure defaults
        allRules = false // activate all available (even unstable) rules.
        config = files(file("$rootDir/detekt.yaml"))
    }

    tasks.withType<Detekt> {
        reports {
            // observe findings in your browser with structure and code snippets
            html.required.set(true)
            // checkstyle like format mainly for integrations like Jenkins
            xml.required.set(true)
            // similar to the console output, contains issue signature to manually edit baseline files
            txt.required.set(true)
        }
    }
}
dependencies {
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.rules.libraries)
}

// Set proper archives name and namespaces
subprojects {
    afterEvaluate {
        plugins.withType<JavaBasePlugin> {
            configure<JavaPluginExtension> {
                archivesName.set("$group-$version")
            }
        }
        plugins.withType<LibraryPlugin> {
            configure<AndroidBaseExtension> {
                namespace = group.toString()
            }
        }
    }
}

// Set dagger params
subprojects {
    afterEvaluate {
        plugins.withType<KspGradleSubplugin> {
            configure<KspExtension> {
                arg("dagger.formatGeneratedSource", "disabled")
                arg("dagger.fastInit", "enabled")
                arg("dagger.ignoreProvisionKeyWildcards", "enabled")
            }
        }
    }
}

// Compose metrics
subprojects {
    afterEvaluate {
        tasks.withType<KotlinCompile> {
            kotlinOptions {
                if (
                    project.findProperty("mindera.composeReports") == "true") {
                    freeCompilerArgs += listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics"
                    )
                    freeCompilerArgs += listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics"
                    )
                }
            }
        }
    }
}

// Set common optIn
subprojects {
    afterEvaluate {
        plugins.withType<KotlinMultiplatformPluginWrapper> {
            configure<KotlinMultiplatformExtension> {
                targets.withType<KotlinNativeTarget> {
                    compilations.configureEach {
                        compilerOptions.configure {
                            with(freeCompilerArgs) {
                                // Export KDoc comments
                                // https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
                                add("-Xexport-kdoc")

                                // Try out preview custom allocator in K/N 1.9
                                // https://kotlinlang.org/docs/whatsnew19.html#preview-of-custom-memory-allocator
                                add("-Xallocator=custom")
                            }
                        }
                    }
                }
            }
        }
    }
}

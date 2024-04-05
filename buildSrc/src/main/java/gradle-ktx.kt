import groovy.text.SimpleTemplateEngine
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.process.ExecSpec
import publish.SwiftPackageConfiguration
import publish.User
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.Locale.ROOT
import java.util.Properties

private fun Task.on(
    directory: File,
    block: ExecSpec.() -> ExecSpec,
): String = ByteArrayOutputStream().use {
    project.exec {
        workingDir = directory
        block()
        standardOutput = it
    }
    return@use it.toString().trim()
}

private fun Project.defaultUser(): User = with(Properties()) {
    rootProject.file("local.properties").let {
        if (!it.exists()) return User()
        @Suppress("BlockingMethodInNonBlockingContext") load(it.reader())
        User(
            name = getProperty("user.name", null),
            email = getProperty("user.email", null)
        )
    }
}

@Suppress("LongParameterList")
fun TaskContainer.registerPublish(
    lib: String,
    type: String,
    repo: String,
    branch: String,
    user: Project.() -> User = { defaultUser() },
    packageConfiguration: Task.() -> SwiftPackageConfiguration = {
        SwiftPackageConfiguration(
            toolVersion = "5.7",
            version = project.version.toString(),
            name = lib,
            platforms = ".iOS(.v13)",
        )
    },
) {
    register("publish${lib}${type}Framework") {
        description = "Publish iOS framework"

        dependsOn("assemble${lib}${type}XCFramework")

        doLast {
            val rootDir = project.rootDir
            val buildDir = project.buildDir
            val publishDir = File(buildDir, "publish").also { it.mkdir() }
            val repoDir = File(publishDir, lib).also { it.deleteRecursively() }

//            on(publishDir) {
//                commandLine("git", "clone", "--depth=1", "--branch", branch, repo, lib)
//            }
//
//            val originalRepo = on(rootDir) {
//                commandLine("git", "remote", "get-url", "origin")
//            }.substringBeforeLast(".git")
//
//            val titleCommand = "git log -1 --format=\"%s\""
//
//            val bodyCommand =
//                "git log -1 --format=\"\$(if [[ -n \$(git status --porcelain) ]]; then echo '[%h-dirty]'; else echo '[%h]'; fi)($originalRepo/-/tree/%H)\""
//
//            val title = on(rootDir) { commandLine("bash", "-c", titleCommand) }
//            val message = on(rootDir) { commandLine("bash", "-c", bodyCommand) }

            repoDir.listFiles()?.run {
                filter { it.isDirectory && it.name.endsWith("xcframework") }
                    .forEach { it.deleteRecursively() }
            }

            project.copy {
                from("${buildDir}/XCFrameworks/${type.lowercase(ROOT)}")
                into(repoDir.absolutePath)
            }

            val config = packageConfiguration()
            SimpleTemplateEngine().createTemplate(
                File("${project.rootDir}/buildSrc/src/main/resources/template", "Package.swift")
            ).make(
                config.properties
            ).writeTo(
                File(repoDir, "Package.swift").apply { createNewFile() }.writer()
            )

//            project.user().run {
//                name?.let {
//                    on(repoDir) { commandLine("git", "config", "user.name", it) }
//                }
//                email?.let {
//                    on(repoDir) { commandLine("git", "config", "user.email", it) }
//                }
//            }
//
//            on(repoDir) { commandLine("git", "add", ".") }
//
//            on(repoDir) { commandLine("git", "commit", "-m", title, "-m", message) }
//
//            on(repoDir) { commandLine("git", "push", "origin", branch) }

            repoDir.deleteRecursively()
        }
    }
}

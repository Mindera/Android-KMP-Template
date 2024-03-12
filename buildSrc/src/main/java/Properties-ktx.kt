import org.gradle.api.Project
import java.util.Properties

private const val BUILD_IOS = "currencyxxchange.build.ios"
private val kfProperties = Properties()

private fun Project.getProperty(key: String, default: String): String {
    if (!kfProperties.containsKey(key)) {
        kfProperties[key] = properties[key] ?: with(Properties()) {
            rootProject.file("local.properties").let {
                if (!it.exists()) return@let default
                load(it.reader())
                getProperty(key, default)
            }
        }
        println("$key=${kfProperties[key]}")
    }
    return kfProperties[key].toString()
}

val Project.iOSEnabled: Boolean
    get() = getProperty(key = BUILD_IOS, default = "true").toBoolean()

fun Project.iOS(
    disabled: () -> Unit = { },
    enabled: () -> Unit,
): Boolean = iOSEnabled.also { if (it) enabled() else disabled() }

package publish

data class SwiftPackageConfiguration(
    val toolVersion: String = "",
    val version: String = "",
    val name: String = "",
    val platforms: String = "",
) {
    internal val properties = mapOf(
        "toolsVersion" to toolVersion,
        "libVersion" to version,
        "name" to name,
        "platforms" to platforms,
    )
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SpaceX"
include(":app:android")
include(":app:desktop")
include(":app:shared")
include(":common:coroutines-ktx")
include(":common:compose-ktx")
include(":common:viewmodel")
include(":common:domain")
include(":common:api:mindera:rest:ktor")
include(":common:api:mindera:rest:ktor:interceptors")
include(":features:launches:composables")
include(":features:launches:presentation")
include(":features:launches:domain")
include(":features:launches:data:remote:mindera:rest:ktor")
include(":features:launches:data:remote:mindera:rest:schema")
include(":features:launches:data")
include(":libraries:precompose")
include(":app:navigation")
include(":features:launches:data:remote:mindera:rest:sample")
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

rootProject.name = "CurrencyExchange"
include(":app:android")
include(":app:desktop")
include(":app:shared")
include(":common:coroutines-ktx")
include(":common:compose-ktx")
include(":common:viewmodel")
include(":common:domain")
include(":common:api:mindera:rest:ktor")
include(":common:api:mindera:rest:ktor:interceptors")
include(":features:currencyexchange:composables")
include(":features:currencyexchange:presentation")
include(":features:currencyexchange:domain")
include(":features:currencyexchange:data:remote:mindera:rest:ktor")
include(":features:currencyexchange:data:remote:mindera:rest:schema")
include(":features:currencyexchange:data")
include(":libraries:precompose")
include(":app:navigation")
include(":features:currencyexchange:data:remote:mindera:rest:sample")
include(":features:currencyexchange:di")

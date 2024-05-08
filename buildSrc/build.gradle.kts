plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        kotlin {
            srcDirs("../buildSrc/src/main/java")
        }
    }
}

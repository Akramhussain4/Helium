rootProject.name = "Helium"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    // Downloads the JDK requested by jvmToolchain() when it isn't installed locally
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// Project repositories stay allowed: the Kotlin/JS and Wasm plugins add their own for Node.js, Yarn and Binaryen downloads
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":html-parser")

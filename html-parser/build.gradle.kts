import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.dokka)
    alias(libs.plugins.mavenPublish)
}

// Test working directories differ per platform (Node runs from build/js), so tests locate their
// resources through an absolute path baked in at build time
val generateTestResourcesPath = tasks.register("generateTestResourcesPath") {
    val resourcesDir = layout.projectDirectory.dir("src/commonTest/resources").asFile.invariantSeparatorsPath
    val outputDir = layout.buildDirectory.dir("generated/testResourcesPath")
    inputs.property("resourcesDir", resourcesDir)
    outputs.dir(outputDir)
    doLast {
        val file = outputDir.get().file("com/theakram/helium/TestResources.kt").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            |package com.theakram.helium
            |
            |internal const val TEST_RESOURCES_DIR: String = "$resourcesDir"
            |""".trimMargin()
        )
    }
}

kotlin {
    explicitApi()

    // Builds and tests on JDK 25, but emits Java 11 bytecode so consumers on older JVMs and Android can use it
    jvmToolchain(25)
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
            freeCompilerArgs.add("-Xjdk-release=11")
        }
        testRuns.named("test") {
            executionTask.configure { useJUnitPlatform() }
        }
    }

    // Tests read the html5lib-tests files from disk, which only Node can do, so browser test runs are disabled.
    // Mocha's default 2s timeout is too short for running every html5lib test in one test on slow CI hosts.
    js {
        browser { testTask { enabled = false } }
        nodejs { testTask { useMocha { timeout = "5m" } } }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser { testTask { enabled = false } }
        nodejs { testTask { useMocha { timeout = "5m" } } }
    }

    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    tvosArm64()
    tvosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosSimulatorArm64()
    linuxX64()
    linuxArm64()
    mingwX64()
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    // Fails `check` when the public API changes without an updated dump in api/ (run updateKotlinAbi)
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()

    sourceSets {
        commonTest {
            kotlin.srcDir(generateTestResourcesPath)
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.io.core)
            }
        }
        jvmTest.dependencies {
            // kotlin-test-junit5 brings an old JUnit by default; the BOM aligns it to the latest release
            implementation(project.dependencies.platform(libs.junit.bom))
        }
    }
}

// Gives Java module-path consumers a stable module name instead of one derived from the jar file name
tasks.named<Jar>("jvmJar") {
    manifest { attributes("Automatic-Module-Name" to "com.theakram.helium") }
}

dokka {
    moduleName = "Helium"
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory = layout.projectDirectory.dir("src")
            remoteUrl("https://github.com/Akramhussain4/Helium/tree/master/html-parser/src")
            remoteLineSuffix = "#L"
        }
    }
}

mavenPublishing {
    publishToMavenCentral()
    // Maven Central requires signatures; skipping without a key keeps publishToMavenLocal working on dev machines
    if (providers.gradleProperty("signingInMemoryKey").isPresent) {
        signAllPublications()
    }

    coordinates(artifactId = "helium")

    pom {
        name = "Helium"
        description = "A spec-compliant HTML5 parser for Kotlin Multiplatform."
        inceptionYear = "2023"
        url = "https://github.com/Akramhussain4/Helium"
        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/license/mit"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "Akramhussain4"
                name = "Mohammed Akram Hussain"
                url = "https://theakram.com"
            }
        }
        scm {
            url = "https://github.com/Akramhussain4/Helium"
            connection = "scm:git:https://github.com/Akramhussain4/Helium.git"
            developerConnection = "scm:git:ssh://git@github.com/Akramhussain4/Helium.git"
        }
    }
}

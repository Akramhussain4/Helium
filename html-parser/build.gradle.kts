import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    alias(libs.plugins.kotlinMultiplatform)
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

    // Fails `check` when the public API changes without an updated dump in api/ (run updateKotlinAbi)
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmTest.dependencies {
            // kotlin-test-junit5 brings an old JUnit by default; the BOM aligns it to the latest release
            implementation(project.dependencies.platform(libs.junit.bom))
        }
    }
}

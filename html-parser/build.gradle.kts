import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    explicitApi()

    // Builds and tests on JDK 21, but emits Java 11 bytecode so consumers on older JVMs and Android can use it
    jvmToolchain(21)
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
            freeCompilerArgs.add("-Xjdk-release=11")
        }
    }

    // Fails `check` when the public API changes without an updated dump in api/ (run updateKotlinAbi)
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

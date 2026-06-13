plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    explicitApi()

    jvmToolchain(21)
    jvm { }

    sourceSets {
        commonMain.dependencies {

        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.json)
        }
    }
}
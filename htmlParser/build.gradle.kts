plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    applyDefaultHierarchyTemplate()
    explicitApi()

    jvmToolchain(11)
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
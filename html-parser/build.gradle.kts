plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    explicitApi()

    jvmToolchain(21)
    jvm { }

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

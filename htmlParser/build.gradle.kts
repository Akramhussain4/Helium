plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    applyDefaultHierarchyTemplate()
    explicitApi()

    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    jvm {
        jvmToolchain(11)
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {

        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
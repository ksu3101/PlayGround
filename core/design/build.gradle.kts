plugins {
    id("kr.swkang.playground.android.library")
    id("kr.swkang.playground.android.library.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "kr.swkang.core.design"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    lintChecks(libs.compose.lint)

    testImplementation(project(":core:test"))
}

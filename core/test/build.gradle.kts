plugins {
    id("kr.swkang.playground.android.library")
    id("kr.swkang.playground.android.library.compose")
    id("kr.swkang.playground.android.hilt")
}

android {
    namespace = "kr.swkang.core.test"

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":core:common"))

    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.mockk)

    api(libs.androidx.paging.testing)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.runner)
    api(libs.androidx.test.rules)
    api(libs.androidx.compose.ui.test)
    api(libs.hilt.android.testing)
}

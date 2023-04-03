plugins {
    id("kr.swkang.playground.android.library")
    id("kr.swkang.playground.android.hilt")
}

android {
    namespace = "kr.swkang.core.network"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)
    testImplementation(project(":core:test"))
}

plugins {
    id("kr.swkang.playground.android.library")
    id("kr.swkang.playground.android.hilt")
}

android {
    namespace = "kr.swkang.network"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
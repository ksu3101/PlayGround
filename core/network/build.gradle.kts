plugins {
    id("kr.swkang.playground.android.library")
    id("kr.swkang.playground.android.library.compose")
    id("kr.swkang.playground.android.hilt")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "kr.swkang.network"
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
}
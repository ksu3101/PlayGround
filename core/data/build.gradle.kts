plugins {
    id("kr.swkang.playground.android.library")
    id("kr.swkang.playground.android.hilt")
}

android {
    namespace = "kr.swkang.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:test"))
}

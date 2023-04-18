plugins {
    id("kr.swkang.playground.android.library")
}

android {
    namespace = "kr.swkang.annotation"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.kotlin.poet)
}

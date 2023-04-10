plugins {
    id("kr.swkang.playground.android.feature")
    id("kr.swkang.playground.android.library.compose")
}

android {
    namespace = "kr.swkang.gallery"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.androidx.palette)

    lintChecks(libs.compose.lint)
}

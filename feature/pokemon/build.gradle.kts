plugins {
    id("kr.swkang.playground.android.feature")
    id("kr.swkang.playground.android.library.compose")
}

android {
    namespace = "kr.swkang.pokemon"
}

dependencies {
    implementation(libs.timber)

    lintChecks(libs.compose.lint)
}
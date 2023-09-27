plugins {
    id("kr.swkang.playground.android.feature")
    id("kr.swkang.playground.android.library.compose")
}

android {
    namespace = "kr.swkang.google_billing"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.google.billing)
}

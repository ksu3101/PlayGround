plugins {
    `kotlin-dsl`
}

group = "kr.swkang.playground.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

// 공통으로 사용되는 플러그인들 클래스를 id 로 선언 한다.
// 선언 될 플러그인이 구현된 클래스의 패키지는 root, 즉 package 관련이 없어야 한다.
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "kr.swkang.playground.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "kr.swkang.playground.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "kr.swkang.playground.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "kr.swkang.playground.android.library.compose"
            implementationClass = "LibraryComposeConventionPlugin"
        }
        register("AndroidLibrary") {
            id = "kr.swkang.playground.android.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "kr.swkang.playground.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidTest") {
            id = "kr.swkang.playground.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
    }
}

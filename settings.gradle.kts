pluginManagement {
    // build-logic : 메인, 서브 모듈들 에서 공통으로 적용 될 내용이 kotlin-dsl로 정의 되어 있음.
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "playground"

// 서브 모듈 들
include(":app")
include(":core:design")
include(":core:network")
include(":core:common")
include(":feature:pokemon")
include(":core:test")

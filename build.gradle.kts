import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.secrets) apply false
}

// detekt 태스크의 report 설정.
tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true) // for sonarcloud
        html.required.set(true)
        txt.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
}

// git hook 을 사용하기 위해 만든 pre-commit 파일을 복사 하는 태스크 설정 및 추가.
tasks.register("installGitHook", Copy::class) {
    if (!file(".git/hooks/pre-commit").exists() || file(".git/hooks/pre-commit").length() != file(".github/pre-commit").length()) {
        // .git/hook/pre-commit 파일이 존재 하거나 수동으로 만든 파일과 다르다면 이 파일을 복사 해서 옳겨준다.
        from(File(rootProject.rootDir, ".github/pre-commit"))
        into(File(rootProject.rootDir, ".git/hooks"))
    }
}
// :app:preBuild (app 모듈의 preBuild 태스크) 시 위의 `installGitHook` 태스크를 먼저 진행 하게 한다.
tasks.getByPath(":app:preBuild").dependsOn(tasks.named("installGitHook"))

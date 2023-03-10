import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import kr.swkang.playground.configureKotlinAndroid
import kr.swkang.playground.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * 안드로이드 기본 설정 플러그인
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
            }
        }
    }

}
import com.android.build.gradle.TestExtension
import kr.swkang.playground.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * 테스트 플러그인
 *
 * @author bmo
 * @since 2023-03-10
 */
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 31
            }
        }
    }

}
import com.android.build.api.dsl.ApplicationExtension
import kr.swkang.playground.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * 컴프즈 플러그인
 *
 * @author bmo
 * @since 2023-03-10
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}
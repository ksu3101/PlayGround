package kr.swkang.gallery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kr.swkang.gallery.GalleryMainScreen
import kr.swkang.gallery.basic.BasicComponentGalleryScreen

const val navDestGalleryMain = "galleryMain"
const val navDestBasicComponentGallery = "basicComponentGallery"

/**
 * 갤러리 메인 화면
 */
fun NavController.navigateToGalleryMain(navOptions: NavOptions? = null) {
    this.navigate(navDestGalleryMain, navOptions)
}

fun NavGraphBuilder.galleryMainScreen() {
    composable(route = navDestGalleryMain) {
        GalleryMainScreen()
    }
}

/**
 * 기본 컴포넌트들의 에제 화면이다.
 *   - Buttons
 *     - Text Button
 *     - Icon Button
 *     - Segment Button
 *   - CheckBox
 *   - Chips
 *   - Radio Button
 *   - Switch
 *   - Slider
 *   - Time Picker
 */
fun NavController.navigateToBasicComponentGallery(navOptions: NavOptions? = null) {
    this.navigate(navDestBasicComponentGallery, navOptions)
}

fun NavGraphBuilder.basicComponentGalleryScreen() {
    composable(route = navDestBasicComponentGallery) {
        BasicComponentGalleryScreen()
    }
}

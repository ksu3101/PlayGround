package kr.swkang.playground.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.swkang.gallery.navigation.basicComponentGalleryScreen
import kr.swkang.gallery.navigation.galleryMainScreen
import kr.swkang.playground.presenter.MainScreen
import kr.swkang.pokemon.navigation.pokeDetailScreen
import kr.swkang.pokemon.navigation.pokeMainScreen

/**
 * 각 컴포즈 화면으로 네비게이션 하기 위해 `NavHost`를 정의 한다.
 *   - `navController` 인스턴스의 `navigate("목적지_문자열)" 함수를 사용해 탐색 한다.
 *
 * @author beemo
 * @since 2023/03/15
 */

internal const val navDestMain = "main"

@Composable
fun PlayGroundNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = navDestMain
) {
    // NavController와 NavHost 를 연결 한다.
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // kotlin DSL 람다를 통해서 네비겨이셔닝 대상의 컴포저블 함수를 제공 하여 탐색할 수 있게 해준다.
        composable(route = navDestMain) {
            MainScreen(navController = navController)
        }
        // 포켓몬 api 메인 화면
        pokeMainScreen()
        // 포켓몬 api 상세 화면
        pokeDetailScreen()

        // 갤러리 메인 화면
        galleryMainScreen()
        // 기본 컴포넌트 갤러리 화면
        basicComponentGalleryScreen()
    }
}

/**
 * 메인 화면으로 탐색을 진행 한다.
 */
fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    this.navigate(navDestMain, navOptions)
}

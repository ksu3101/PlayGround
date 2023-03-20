package kr.swkang.playground.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kr.swkang.playground.presenter.MainScreen
import kr.swkang.pokemon.PokeScreen
import kr.swkang.pokemon.PokeViewModel
import kr.swkang.pokemon.details.PokeDetailsScreen

/**
 * 각 컴포즈 화면으로 네비게이션 하기 위해 `NavHost`를 정의 한다.
 *   - `navController` 인스턴스의 `navigate("목적지_문자열)" 함수를 사용해 탐색 한다.
 *
 * @author beemo
 * @since 2023/03/15
 */

internal const val navDestMain = "main"
internal const val navDestPoke = "poke"
internal const val pokeDetailArgumentKey = "pokemonId"
internal const val navDeskPokeDetails = "pokeDetails/"

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
        composable(route = navDestPoke) {
            PokeScreen(
                // navigation graph 에서 ViewModel 의 인스턴스를 유지하기 위해 `hiltViewModel()`을 사용 한다.
                viewModel = hiltViewModel()
            )
        }
        composable(
            route = "$navDeskPokeDetails{$pokeDetailArgumentKey}",
            arguments = listOf(navArgument(pokeDetailArgumentKey) { type = NavType.IntType })
        ) {
            PokeDetailsScreen()
        }
    }
}

/**
 * 메인 화면으로 탐색을 진행 한다.
 */
fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    this.navigate(navDestMain, navOptions)
}

/**
 * Poke 화면으로 탐색을 진행 한다.
 */
fun NavController.navigateToPoke(navOptions: NavOptions? = null) {
    this.navigate(navDestPoke, navOptions)
}

/**
 * Poke 상세 화면으로 탐색을 진행 한다.
 */
fun NavController.navigateToPokeDetails(pokemonId: Int, navOption: NavOptions? = null) {
    this.navigate("$navDeskPokeDetails{$pokemonId}", navOption)
}
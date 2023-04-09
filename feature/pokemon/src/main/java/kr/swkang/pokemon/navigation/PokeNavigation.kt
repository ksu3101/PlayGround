package kr.swkang.pokemon.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.swkang.pokemon.PokeScreen
import kr.swkang.pokemon.details.PokeDetailsScreen

internal const val navDestPoke = "poke"
internal const val pokeDetailArgumentKey = "pokemonId"
internal const val navDeskPokeDetails = "pokeDetails/"

/**
 * Poke 화면으로 탐색을 진행 한다.
 */
fun NavController.navigateToPoke(navOptions: NavOptions? = null) {
    this.navigate(navDestPoke, navOptions)
}

fun NavGraphBuilder.pokeMainScreen() {
    composable(route = navDestPoke) {
        PokeScreen(
            // navigation graph 에서 ViewModel 의 인스턴스를 유지하기 위해 `hiltViewModel()`을 사용 한다.
            viewModel = hiltViewModel()
        )
    }
}

/**
 * Poke 상세 화면으로 탐색을 진행 한다.
 */
fun NavController.navigateToPokeDetails(pokemonId: Int, navOption: NavOptions? = null) {
    this.navigate("$navDeskPokeDetails{$pokemonId}", navOption)
}

fun NavGraphBuilder.pokeDetailScreen() {
    composable(
        route = "$navDeskPokeDetails{$pokeDetailArgumentKey}",
        arguments = listOf(navArgument(pokeDetailArgumentKey) { type = NavType.IntType })
    ) {
        PokeDetailsScreen()
    }
}

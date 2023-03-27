package kr.swkang.pokemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kr.swkang.core.domain.pokemon.model.SimplePokemonInfos
import kr.swkang.design.components.OnLoadingProgressWithDim

/**
 * 포켓몬 목록 화면
 *
 * @author bmo
 * @since 2023-03-12
 */

@Composable
fun PokeScreen(
    viewModel: PokeViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isLoading: Boolean by viewModel.isLoading.collectAsState(initial = false)
    val pokemons = viewModel.getPokemons().collectAsLazyPagingItems()
    PokeScreenDetails(
        context = context,
        pokemons = pokemons,
        isLoading = isLoading,
        modifier = modifier
    )
}

@Composable
fun PokeScreenDetails(
    context: Context,
    pokemons: LazyPagingItems<SimplePokemonInfos>,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(
                items = pokemons,
                key = { it.url }
            ) { pokemon ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(text = pokemon?.name ?: "") // todo : 공통 오류 처리
                }
                Divider()
            }
        }

        // 포켓몬 목록 첫번째 로드
        when (val state = pokemons.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    state.error.message?.ifEmpty { "Unknown error occure." },
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoadState.Loading -> {
                OnLoadingProgressWithDim()
            }
            else -> {
                // nothing to do..
            }
        }

        // 포켓못 목록의 페이징 ui 처리
        when (val state = pokemons.loadState.append) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    state.error.message?.ifEmpty { "Unknown error occure." },
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoadState.Loading -> {
                OnLoadingProgressWithDim()
            }
            else -> {
                // nothing to do..
            }
        }

        if (isLoading) {
            OnLoadingProgressWithDim()
        }
    }
}

@Preview
@Composable
fun PokeScreenDetailsPreView() {
    // PlayGroundTheme {
    //     PokeScreenDetails(
    //         isLoading = true
    //     )
    // }
}

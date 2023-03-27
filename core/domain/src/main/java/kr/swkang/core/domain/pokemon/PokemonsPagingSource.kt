package kr.swkang.core.domain.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.swkang.core.data.PokeRepository
import kr.swkang.core.domain.pokemon.model.SimplePokemonInfos

/**
 * @author bmo
 * @since 2023-03-27
 */
class PokemonsPagingSource(
    private val pokeRepository: PokeRepository
) : PagingSource<Int, SimplePokemonInfos>() {
    override fun getRefreshKey(state: PagingState<Int, SimplePokemonInfos>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimplePokemonInfos> {
        return try {
            val page = params.key ?: 0
            val response = pokeRepository.getPokemons(
                offset = page * GetPokemonsUseCase.LOAD_POKEMONS_DEF_LIMIT,
                limit = GetPokemonsUseCase.LOAD_POKEMONS_DEF_LIMIT
            )
            LoadResult.Page(
                data = response.results.map {
                    SimplePokemonInfos(
                        name = it.name,
                        id = it.url.parsePokemonId(),
                        url = it.url
                    )
                },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.next == null) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

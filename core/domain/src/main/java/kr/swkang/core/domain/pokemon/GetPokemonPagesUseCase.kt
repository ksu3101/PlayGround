package kr.swkang.core.domain.pokemon

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject
import kr.swkang.common.exts.isNumber
import kr.swkang.core.data.PokeRepository

/**
 * @author bmo
 * @since 2023-03-21
 */
class GetPokemonPagesUseCase @Inject constructor(
    private val pokeRepository: PokeRepository
) {
    operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = LOAD_POKEMONS_DEF_LIMIT
        ),
        pagingSourceFactory = {
            PokemonsPagingSource(pokeRepository)
        }
    ).flow

    companion object {
        const val LOAD_POKEMONS_DEF_LIMIT = 20
        const val NOT_AVAILABLE_POKEMON_ID = -1
    }
}

/**
 * 문자열에서 포켓몬 id 의 패스를 파싱 하여 반환 한다. 주어진 문자열이 포켓몬 api와 관련이 없거나
 * 찾지 못했다면 `NOT_AVAILABLE_POKEMON_ID : -1` 을 반환한다.
 */
internal fun String?.parsePokemonId(): Int {
    if (isNullOrEmpty() || !startsWith("https://pokeapi.co/api/v2/pokemon")) {
        return GetPokemonPagesUseCase.NOT_AVAILABLE_POKEMON_ID
    }
    val path = substring(indexOf("//"))
    if (path.isNotEmpty()) {
        val splitedPath = path.split("/")
        for (i in splitedPath.size - 1 downTo 0) {
            if (splitedPath[i].isNumber()) {
                return splitedPath[i].toInt()
            }
        }
    }
    return GetPokemonPagesUseCase.NOT_AVAILABLE_POKEMON_ID
}

package kr.swkang.core.domain

import javax.inject.Inject
import kr.swkang.common.exts.isNumber
import kr.swkang.core.data.PokeRepository
import kr.swkang.core.domain.model.SimplePokemonInfos

/**
 * @author bmo
 * @since 2023-03-21
 */
class GetPokemonsUseCase @Inject constructor(
    private val pokeRepository: PokeRepository
) {
    suspend operator fun invoke(
        offset: Int,
        limit: Int
    ): List<SimplePokemonInfos> =
        pokeRepository.getPokemons(
            offset = offset,
            limit = limit
        ).results
            .map {
                SimplePokemonInfos(
                    name = it.name,
                    id = parsePokemonId(it.url),
                    url = it.url
                )
            }

    companion object {
        const val NOT_AVAILABLE_POKEMON_ID = -1

        /**
         * `url` 문자열에서 포켓몬 id 의 패스를 파싱 하여 반환 한다. 주어진 문자열이 포켓몬 api와 관련이 없거나
         * 찾지 못했다면 `NOT_AVAILABLE_POKEMON_ID : -1` 을 반환한다.
         */
        fun parsePokemonId(url: String?): Int {
            if (url.isNullOrEmpty() || !url.startsWith("https://pokeapi.co/api/v2/pokemon")) {
                return NOT_AVAILABLE_POKEMON_ID
            }
            val path = url.substring(url.indexOf("//"))
            if (path.isNotEmpty()) {
                val splitedPath = path.split("/")
                for (i in splitedPath.size - 1 downTo 0) {
                    if (splitedPath[i].isNumber()) {
                        return splitedPath[i].toInt()
                    }
                }
            }
            return NOT_AVAILABLE_POKEMON_ID
        }
    }
}

package kr.swkang.core.domain

import javax.inject.Inject
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
                    id = 0, // todo : parse id.
                    url = it.url
                )
            }
}

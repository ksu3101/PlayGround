package kr.swkang.core.data

import kr.swkang.network.retrofit.poke.PokemonsResponse

/**
 * @author bmo
 * @since 2023-03-21
 */
interface PokeRepository {
    suspend fun getPokemons(
        offset: Int,
        limit: Int
    ): PokemonsResponse
}

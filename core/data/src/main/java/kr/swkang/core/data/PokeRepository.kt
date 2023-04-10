package kr.swkang.core.data

import kr.swkang.network.retrofit.poke.dto.PokemonResponse
import kr.swkang.network.retrofit.poke.dto.PokemonsResponse

/**
 * @author bmo
 * @since 2023-03-21
 */
interface PokeRepository {
    suspend fun getPokemons(
        offset: Int,
        limit: Int
    ): PokemonsResponse

    suspend fun getPokemon(
        pokemonId: Int
    ): PokemonResponse
}

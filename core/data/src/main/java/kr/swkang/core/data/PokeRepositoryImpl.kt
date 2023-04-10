package kr.swkang.core.data

import javax.inject.Inject
import kr.swkang.network.retrofit.poke.PokeApi
import kr.swkang.network.retrofit.poke.dto.PokemonResponse
import kr.swkang.network.retrofit.poke.dto.PokemonsResponse

class PokeRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokeRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): PokemonsResponse =
        pokeApi.getPokemons(
            offset = offset,
            limit = limit
        )

    override suspend fun getPokemon(pokemonId: Int): PokemonResponse =
        pokeApi.getPokemon(pokemonId)
}

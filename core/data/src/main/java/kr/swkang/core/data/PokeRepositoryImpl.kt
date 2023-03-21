package kr.swkang.core.data

import javax.inject.Inject
import kr.swkang.network.retrofit.poke.PokeApi
import kr.swkang.network.retrofit.poke.PokemonsResponse

class PokeRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokeRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): PokemonsResponse =
        pokeApi.getPokemons(
            offset = offset,
            limit = limit
        )
}

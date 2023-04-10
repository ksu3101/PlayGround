package kr.swkang.network.retrofit.poke

import kr.swkang.network.retrofit.poke.dto.PokemonResponse
import kr.swkang.network.retrofit.poke.dto.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * poke api
 *
 * @author bmo
 * @since 2023-03-12
 */
interface PokeApi {
    /**
     * 포켓몬의 목록을 가져 온다.
     *
     * @param offset 가져올 목록의 시작 인덱스.
     * @param limit 한번에 불러올 포켓몬의 수.
     */
    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("offset")
        offset: Int,
        @Query("limit")
        limit: Int
    ): PokemonsResponse

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemon(
        @Path("id")
        pokemonId: Int
    ): PokemonResponse
}

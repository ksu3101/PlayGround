package kr.swkang.network.retrofit.poke

import retrofit2.http.GET
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
}

/**
 * - count : 가져온 포켓몬 목록의 개수
 * - next : 다음 가져올 포켓몬 목록이 있을 경우에 가져올 url
 * - previous : 이전에 가져왔던 포켓몬 목록의 url
 * - results : 목록 데이터
 */
data class PokemonsResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonResponse>
)

/**
 * - name : 포켓몬의 이름
 * - url : 포켓몬에 대한 상세 정보를 알 수 있는 url
 */
data class PokemonResponse(
    val name: String,
    val url: String = ""
)

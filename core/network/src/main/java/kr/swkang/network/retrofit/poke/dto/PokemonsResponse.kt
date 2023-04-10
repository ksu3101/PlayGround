package kr.swkang.network.retrofit.poke.dto

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
    val results: List<PokemonDatas>
)

/**
 * - name : 포켓몬의 이름
 * - url : 포켓몬에 대한 상세 정보를 알 수 있는 url
 */
data class PokemonDatas(
    val name: String,
    val url: String = ""
)

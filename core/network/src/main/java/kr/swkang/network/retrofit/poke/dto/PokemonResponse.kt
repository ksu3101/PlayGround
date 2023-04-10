package kr.swkang.network.retrofit.poke.dto

import com.squareup.moshi.Json

/**
 * 포켓몬 데이터.
 *   - 당장 필요없는 데이터는 제거 하였음.
 */
data class PokemonResponse(
    val id: Int,
    val name: String,
    @Json(name = "base_experience")
    val baseExperience: Int,
    val height: Int,
    @Json(name = "is_default")
    val isDeafult: Boolean,
    val order: Int,
    val weight: Int,
    val species: NamedAPIResourceDatas,
    val abilities: List<PokemonAbilityDatas>,
    val forms: List<NamedAPIResourceDatas>,
    val sprites: List<PokemonSpritesDatas>,
    val stats: List<PokemonStatDatas>,
    val types: List<PokemonTypeDatas>
)

data class PokemonAbilityDatas(
    @Json(name = "is_hidden")
    val isHidden: Boolean,
    val slot: Int,
    val ability: NamedAPIResourceDatas
)

data class NamedAPIResourceDatas(
    val name: String,
    val url: String
)

data class PokemonSpritesDatas(
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "back_default")
    val backDefault: String
)

data class PokemonStatDatas(
    val stat: NamedAPIResourceDatas,
    val effort: Int,
    @Json(name = "base_stat")
    val baseStat: Int
)

data class PokemonTypeDatas(
    val slot: Int,
    val type: NamedAPIResourceDatas
)

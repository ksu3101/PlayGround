package kr.swkang.core.domain.pokemon.model

/**
 * @author bmo
 * @since 2023-04-10
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDeafult: Boolean,
    val order: Int,
    val weight: Int,
    val species: NamedDatas,
    val abilities: List<PokemonAbility>,
    val forms: List<NamedDatas>,
    val sprites: List<PokemonSprites>,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>
)

data class PokemonAbility(
    val isHidden: Boolean,
    val slot: Int,
    val ability: NamedDatas
)

data class PokemonSprites(
    val frontDefault: String,
    val backDefault: String
)

data class PokemonStat(
    val stat: NamedDatas,
    val effort: Int,
    val baseStat: Int
)

data class PokemonType(
    val slot: Int,
    val type: NamedDatas
)

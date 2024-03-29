package kr.swkang.core.domain.pokemon.model

/**
 * @author bmo
 * @since 2023-03-21
 */
data class SimplePokemonInfos(
    val name: String,
    val id: Int,
    val url: String = ""
)

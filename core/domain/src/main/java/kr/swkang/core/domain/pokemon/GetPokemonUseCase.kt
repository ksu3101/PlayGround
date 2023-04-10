package kr.swkang.core.domain.pokemon

import javax.inject.Inject
import kr.swkang.core.data.PokeRepository
import kr.swkang.core.domain.pokemon.model.NamedDatas
import kr.swkang.core.domain.pokemon.model.Pokemon
import kr.swkang.core.domain.pokemon.model.PokemonAbility
import kr.swkang.core.domain.pokemon.model.PokemonSprites
import kr.swkang.core.domain.pokemon.model.PokemonStat
import kr.swkang.core.domain.pokemon.model.PokemonType
import kr.swkang.network.retrofit.poke.dto.NamedAPIResourceDatas
import kr.swkang.network.retrofit.poke.dto.PokemonAbilityDatas
import kr.swkang.network.retrofit.poke.dto.PokemonResponse
import kr.swkang.network.retrofit.poke.dto.PokemonSpritesDatas
import kr.swkang.network.retrofit.poke.dto.PokemonStatDatas
import kr.swkang.network.retrofit.poke.dto.PokemonTypeDatas

/**
 *
 * @author bmo
 * @since 2023-04-10
 */
class GetPokemonUseCase @Inject constructor(
    private val pokeRepository: PokeRepository
) {
    suspend operator fun invoke(pokemonId: Int): Pokemon =
        pokeRepository.getPokemon(pokemonId).datasToPokemon()
}

private fun PokemonResponse.datasToPokemon() =
    Pokemon(
        id = id,
        name = name,
        baseExperience = baseExperience,
        height = height,
        isDeafult = isDeafult,
        order = order,
        weight = weight,
        species = species.toNamedDatas(),
        abilities = abilities.datasToAbilities(),
        forms = forms.map { NamedDatas(name = it.name, url = it.url) },
        sprites = sprites.datasToSprites(),
        stats = stats.datasToStats(),
        types = types.datasToTypes()
    )

fun NamedAPIResourceDatas.toNamedDatas() =
    NamedDatas(
        name = name,
        url = url
    )

private fun List<PokemonAbilityDatas>.datasToAbilities() =
    map {
        PokemonAbility(
            isHidden = it.isHidden,
            slot = it.slot,
            ability = it.ability.toNamedDatas()
        )
    }

private fun List<PokemonSpritesDatas>.datasToSprites() =
    map {
        PokemonSprites(
            frontDefault = it.frontDefault,
            backDefault = it.backDefault
        )
    }

private fun List<PokemonStatDatas>.datasToStats() =
    map {
        PokemonStat(
            stat = it.stat.toNamedDatas(),
            effort = it.effort,
            baseStat = it.baseStat
        )
    }

private fun List<PokemonTypeDatas>.datasToTypes() =
    map {
        PokemonType(
            slot = it.slot,
            type = it.type.toNamedDatas()
        )
    }

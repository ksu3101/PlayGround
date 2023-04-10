package kr.swkang.core.domain

import kr.swkang.core.domain.pokemon.GetPokemonPagesUseCase
import kr.swkang.core.domain.pokemon.parsePokemonId
import kr.swkang.core.test.shouldBe
import org.junit.Test

class ParseIdTest {

    /**
     * - given
     *     - pokemon id 는 "14".
     *     - url은 규격에 맞으며 문제가 없음.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - "14"를 Integer 타입 `14` 로 반환 한다.
     */
    @Test
    fun parsePokemonIdCaseOne_fromCorrectUrl_findPokemonId() {
        val pokemonId = 14
        val url = "https://pokeapi.co/api/v2/pokemon/$pokemonId/"

        val id = url.parsePokemonId()

        id shouldBe pokemonId
    }

    /**
     * - given
     *     - pokemon id 는 "1".
     *     - url은 규격에 맞으며 문제가 없음.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - "1"를 Integer 타입 `1` 로 반환 한다.
     */
    @Test
    fun parsePokemonIdCaseTwo_fromCorrectUrl_findPokemonId() {
        val pokemonId = 1
        val url = "https://pokeapi.co/api/v2/pokemon/$pokemonId/"

        val id = url.parsePokemonId()

        id shouldBe pokemonId
    }

    /**
     * - given
     *     - pokemon id 는 "1234567890".
     *     - url은 규격에 맞으며 문제가 없음.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - "1234567890"를 Integer 타입 `1234567890` 로 반환 한다.
     */
    @Test
    fun parsePokemonIdCaseThree_fromCorrectUrl_findPokemonId() {
        val pokemonId = 1234567890
        val url = "https://pokeapi.co/api/v2/pokemon/$pokemonId"

        val id = url.parsePokemonId()

        id shouldBe pokemonId
    }

    /**
     * - given
     *     - pokemon id 는 "143".
     *     - url은 `https` 아닌 `http`이다.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - 파싱 에러 코드인 `-1` 인 `NOT_AVAILABLE_POKEMON_ID`을 반환 한다.
     */
    @Test
    fun parsePokemonIdCase_fromNotHttpsUrl_returnNotAvailablePokemonId() {
        val pokemonId = 143
        val url = "http://pokeapi.co/api/v2/pokemon/$pokemonId"

        val id = url.parsePokemonId()

        id shouldBe GetPokemonPagesUseCase.NOT_AVAILABLE_POKEMON_ID
    }

    /**
     * - given
     *     - pokemon id 는 "143".
     *     - url의 도메인이 규격과 다르다.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - 파싱 에러 코드인 `-1` 인 `NOT_AVAILABLE_POKEMON_ID`을 반환 한다.
     */
    @Test
    fun parsePokemonIdCase_fromWrongUrl_returnNotAvailablePokemonId() {
        val pokemonId = 143
        val url = "http://google.com/$pokemonId"

        val id = url.parsePokemonId()

        id shouldBe GetPokemonPagesUseCase.NOT_AVAILABLE_POKEMON_ID
    }

    /**
     * - given
     *     - pokemon id 는 "143".
     *     - url이 비어 있다.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - 파싱 에러 코드인 `-1` 인 `NOT_AVAILABLE_POKEMON_ID`을 반환 한다.
     */
    @Test
    fun parsePokemonIdCaseTwo_fromEmptyUrls_returnNotAvailablePokemonId() {
        val url = ""

        val id = url.parsePokemonId()

        id shouldBe GetPokemonPagesUseCase.NOT_AVAILABLE_POKEMON_ID
    }

    /**
     * - given
     *     - pokemon id 는 "143".
     *     - url은 `null`이다.
     * - when
     *     - `GetPokemonsUseCase.parsePokemonId(url)`
     * - then
     *     - 파싱 에러 코드인 `-1` 인 `NOT_AVAILABLE_POKEMON_ID`을 반환 한다.
     */
    @Test
    fun parsePokemonIdCaseThree_fromNullableStrings_returnNotAvailablePokemonId() {
        val url = null

        val id = url.parsePokemonId()

        id shouldBe GetPokemonPagesUseCase.NOT_AVAILABLE_POKEMON_ID
    }
}

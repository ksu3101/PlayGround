package kr.swkang.pokemon

import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kr.swkang.core.domain.pokemon.GetPokemonPagesUseCase
import kr.swkang.core.domain.pokemon.GetPokemonUseCase
import org.junit.Before

/**
 * Paging test
 *  - https://developer.android.com/topic/libraries/architecture/paging/test
 * @author bmo
 * @since 2023-04-03
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PokeScreenTest {
    private lateinit var viewModel: PokeViewModel
    private val getPokemonPagesUseCase: GetPokemonPagesUseCase = mockk()
    private val getPokemonUseCase: GetPokemonUseCase = mockk()

    @Before
    fun setUp() {
        this.viewModel = PokeViewModel(getPokemonPagesUseCase, getPokemonUseCase)
    }

    // @Test
    fun testReceivePokemons_receivedSuccess() = runTest {
        // todo
    }
}

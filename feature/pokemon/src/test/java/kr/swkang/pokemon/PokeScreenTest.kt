package kr.swkang.pokemon

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import kr.swkang.core.domain.pokemon.GetPokemonPagesUseCase
import kr.swkang.core.domain.pokemon.model.SimplePokemonInfos
import org.junit.Before
import org.junit.Test

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

    @Before
    fun setUp() {
        this.viewModel = PokeViewModel(getPokemonPagesUseCase)
    }

    @Test
    fun testReceivePokemons_receivedSuccess() = runTest {
        val items: Flow<PagingData<SimplePokemonInfos>> = viewModel.getPokemonPages()

        val itemsSnapShot: List<SimplePokemonInfos> = items.asSnapshot(
            coroutineScope = this
        ) {
        }
    }
}

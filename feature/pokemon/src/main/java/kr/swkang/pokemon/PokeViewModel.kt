package kr.swkang.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.swkang.core.domain.pokemon.GetPokemonPagesUseCase
import kr.swkang.core.domain.pokemon.GetPokemonUseCase
import kr.swkang.core.domain.pokemon.model.Pokemon
import kr.swkang.core.domain.pokemon.model.SimplePokemonInfos

/**
 * @author bmo
 * @since 2023-03-12
 */
@HiltViewModel
class PokeViewModel @Inject constructor(
    private val getPokemonPagesUseCase: GetPokemonPagesUseCase,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {
    private val _pokemonPublisher = MutableSharedFlow<Pokemon>()
    val pokemonListener = _pokemonPublisher.asSharedFlow()

    /**
     * 포켓몬 목록 페이지를 불러 온다.
     * 페이징 처리는 내부에서 하기 때문에 외부에서는 몰라도 된다.
     */
    fun getPokemonPages(): Flow<PagingData<SimplePokemonInfos>> = getPokemonPagesUseCase().cachedIn(
        viewModelScope
    )

    /**
     * `pokemonId` 에 해당하는 포켓몬 정보를 요청 한다.
     */
    fun requestPokemonDatas(pokemonId: Int) {
        viewModelScope.launch {
            val pokemon = getPokemonUseCase(pokemonId)
            _pokemonPublisher.emit(pokemon)
        }
    }
}

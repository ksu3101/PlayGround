package kr.swkang.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kr.swkang.core.domain.pokemon.GetPokemonsUseCase
import kr.swkang.core.domain.pokemon.model.SimplePokemonInfos

/**
 * @author bmo
 * @since 2023-03-12
 */
@HiltViewModel
class PokeViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {
    /**
     * compose, lazy, paging3
     */
    fun getPokemons(): Flow<PagingData<SimplePokemonInfos>> = getPokemonsUseCase().cachedIn(
        viewModelScope
    )
}

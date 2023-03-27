package kr.swkang.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
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
    private val _isLoading = MutableSharedFlow<Boolean>()
    val isLoading = _isLoading.asSharedFlow()

    /**
     * compose, lazy, paging3
     * 참고 : https://proandroiddev.com/pagination-in-jetpack-compose-with-and-without-paging-3-e45473a352f4
     */
    fun getPokemons(): Flow<PagingData<SimplePokemonInfos>> = getPokemonsUseCase().cachedIn(
        viewModelScope
    )

    fun startLoading() {
        viewModelScope.launch {
            _isLoading.emit(true)
            // for testing.
            delay(2500)
            _isLoading.emit(false)
        }
    }
}

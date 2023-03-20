package kr.swkang.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author bmo
 * @since 2023-03-12
 */
@HiltViewModel
class PokeViewModel @Inject constructor(
) : ViewModel() {
    private val _isLoading = MutableSharedFlow<Boolean>()
    val isLoading = _isLoading.asSharedFlow()

    fun startLoading() {
        viewModelScope.launch {
            _isLoading.emit(true)
            // for testing.
            delay(2500)
            _isLoading.emit(false)
        }
    }
}

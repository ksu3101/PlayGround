package kr.swkang.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.swkang.design.components.OnLoadingProgressWithDim
import kr.swkang.design.theme.PlayGroundTheme

/**
 * 포켓몬 목록 화면
 *
 * @author bmo
 * @since 2023-03-12
 */

@Composable
fun PokeScreen(
    viewModel: PokeViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading: Boolean by viewModel.isLoading.collectAsState(initial = false)
    PokeScreenDetails(
        isLoading = isLoading,
        modifier = modifier.clickable { viewModel.startLoading() }
    )
}

@Composable
fun PokeScreenDetails(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "POKE"
        )

        if (isLoading) {
            OnLoadingProgressWithDim()
        }
    }
}

@Preview
@Composable
fun PokeScreenDetailsPreView() {
    PlayGroundTheme {
        PokeScreenDetails(
            isLoading = true
        )
    }
}

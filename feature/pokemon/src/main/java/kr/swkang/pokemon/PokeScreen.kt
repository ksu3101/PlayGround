package kr.swkang.pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 포켓몬 목록 화면
 *
 * @author bmo
 * @since 2023-03-12
 */

@Composable
fun PokeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "POKE"
        )
    }
}
package kr.swkang.playground.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kr.swkang.design.components.PlayGroundTextButton
import kr.swkang.design.components.PlayGroundTopAppBar
import kr.swkang.design.theme.PlayGroundTheme
import kr.swkang.playground.R
import kr.swkang.playground.navigation.navigateToMain

/**
 * 메인 화면
 *
 * @author bmo
 * @since 2023-03-10
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            PlayGroundTopAppBar(
                titleTextResId = R.string.app_name,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                PlayGroundTextButton(
                    onClick = {
                        navController.navigateToMain()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = "PokeAPI"
                )
            }
        }
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun MainScreenPreViewLight() {
    PlayGroundTheme {
        MainScreen()
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun MainScreenPreViewDark() {
    PlayGroundTheme {
        MainScreen()
    }
}
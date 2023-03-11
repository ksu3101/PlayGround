package kr.swkang.playground.presenter

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kr.swkang.design.theme.PlayGroundTheme
import kr.swkang.playground.R

/**
 * @author bmo
 * @since 2023-03-10
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    stringResource(id = R.string.app_name)
                }
            )
        }
    ) { paddingValues ->
        paddingValues
    }
}

@Preview
@Composable
fun MainScreenPreView() {
    PlayGroundTheme {
        MainScreen()
    }
}
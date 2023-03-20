package kr.swkang.playground.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kr.swkang.design.components.PlayGroundTextButton
import kr.swkang.design.components.PlayGroundTopAppBar
import kr.swkang.design.theme.PlayGroundTheme
import kr.swkang.playground.R
import kr.swkang.playground.navigation.PlayGroundNavHost
import kr.swkang.playground.navigation.navDestMain
import kr.swkang.playground.navigation.navigateToPoke

/**
 * 메인 화면
 *
 * @author bmo
 * @since 2023-03-10
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayGroundMain(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    val isShowBackButton = when (currentRoute.value?.destination?.route) {
        navDestMain -> false
        else -> true
    }
    val systemUiController = rememberSystemUiController()
    val useDarkIcon = !isSystemInDarkTheme()
    Scaffold(
        topBar = {
            PlayGroundTopAppBar(
                titleText = stringResource(id = R.string.app_name),
                isShowBackButton = isShowBackButton,
            ) {
                // <- 버튼 누르면, 이전 화면으로 이동.
                navController.navigateUp()
            }
        }
    ) { innerPadding ->
        // NavHost 연결.
        PlayGroundNavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = navDestMain
        )

        // (accompanist) 최상단 시스템 바 배경, 아이콘 컬러 설정.
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,  // 상단 시스템 바 배경을 투명으로 하여 배경 색을 보인다.
                darkIcons = useDarkIcon     // 아이콘 다크 테마에 따른 설정
            )
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        PlayGroundTextButton(
            onClick = {
                navController.navigateToPoke()
            },
            modifier = Modifier.fillMaxWidth(),
            text = "PokeAPI"
        )
    }
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
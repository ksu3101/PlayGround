package kr.swkang.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import kr.swkang.design.theme.PlayGroundTheme
import kr.swkang.playground.presenter.MainActivityViewModel
import kr.swkang.playground.presenter.PlayGroundMain

/**
 * @author bmo
 * @since 2023-03-10
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // decor fitting 시스템 윈도우 설정을 끈다.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PlayGroundTheme {
                PlayGroundMain()
            }
        }
    }
}
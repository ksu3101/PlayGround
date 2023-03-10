package kr.swkang.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.swkang.design.theme.PlayGroundTheme
import kr.swkang.playground.presenter.MainActivityViewModel
import kr.swkang.playground.presenter.MainScreen

/**
 * @author bmo
 * @since 2023-03-10
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayGroundTheme {
                MainScreen()
            }
        }
    }
}
package kr.swkang.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.swkang.design.theme.PlayGroundTheme

/**
 * @author bmo
 * @since 2023-03-10
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayGroundTheme {
            }
        }
    }
}
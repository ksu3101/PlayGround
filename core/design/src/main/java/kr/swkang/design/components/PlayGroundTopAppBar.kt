package kr.swkang.design.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author beemo
 * @since 2023/03/15
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayGroundTopAppBar(
    titleText: String,
    isShowBackButton: Boolean,
    modifier: Modifier = Modifier,
    onNavBackBtnClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = titleText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (isShowBackButton) {
                IconButton(onClick = onNavBackBtnClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
            // else {
            //     null
            // }
        }
    )
}

@Preview
@Composable
fun PlayGroundTopAppBarPreview() {
    PlayGroundTopAppBar(
        titleText = "타이틀 텍스트",
        isShowBackButton = true
    ) {
    }
}

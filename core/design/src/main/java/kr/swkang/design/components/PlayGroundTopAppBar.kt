package kr.swkang.design.components

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow

/**
 * @author beemo
 * @since 2023/03/15
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayGroundTopAppBar(
    @StringRes titleTextResId: Int,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = titleTextResId),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}

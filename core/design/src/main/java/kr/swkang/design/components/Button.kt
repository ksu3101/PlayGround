package kr.swkang.design.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author bmo
 * @since 2023-03-10
 */

@Composable
fun PlayGroundTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    text: CharSequence
) {
    PlayGroundButton(
        onClick = onClick,
        modifier = modifier,
        isEnable = isEnable,
        content = {
            Text(text.toString())
        }
    )
}

@Composable
fun PlayGroundButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnable,
        content = content
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
fun PlayGroundTextButtonPreView() {
    PlayGroundTextButton(
        onClick = { },
        text = "테스트 문구"
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xffffff
)
@Composable
fun PlayGroundTextButtonPreViewNight() {
    PlayGroundTextButton(
        onClick = { },
        isEnable = true,
        text = "테스트 문구"
    )
}

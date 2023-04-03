package kr.swkang.design.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.swkang.design.theme.PlayGroundTheme

/**
 * 로딩 전체 화면 dim, 프로그레스
 *
 * @author beemo
 * @since 2023/03/20
 */

@Composable
fun OnLoadingProgressWithDim(
    modifier: Modifier = Modifier,
    isRectanbleBoxShowing: Boolean = false,
    background: Color = Color.Black.copy(alpha = 0.65f),
    progressBarColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // disable ripple effect.
                onClick = {
                    // consume this click event.
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isRectanbleBoxShowing) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.4f)
                    )
            )
        }
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = progressBarColor,
            strokeWidth = 5.dp
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun OnLoadingProgressWithDimPreview() {
    PlayGroundTheme {
        // if (isLoading) { ...
        OnLoadingProgressWithDim(
            isRectanbleBoxShowing = true
        )
    }
}

package com.mashup.twotoo.presenter.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

/**
 * @Created by 김현국 2023/06/04
 */

data class HomeShotCountTextData(
    val count: Int = 4,
)

@Composable
fun HomeShotCountText(
    homeShotCountTextData: HomeShotCountTextData,
) {
    Text(
        "콕 찌르기 (${homeShotCountTextData.count}/5",
        color = Color(0xFF4A2B00),
        fontSize = 14.sp,
    )
}

@Preview
@Composable
fun PreviewHomeShotCountText() {
    TwoTooTheme {
        HomeShotCountText(homeShotCountTextData = HomeShotCountTextData())
    }
}

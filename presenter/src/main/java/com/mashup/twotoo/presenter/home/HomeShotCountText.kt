package com.mashup.twotoo.presenter.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

/**
 * @Created by 김현국 2023/06/04
 */



@Composable
fun HomeShotCountText(
    homeShotCountTextData: HomeShotCountTextData,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "콕 찌르기 (${homeShotCountTextData.count}/5)",
        color = TwoTooTheme.color.mainBrown,
        style = TwoTooTheme.typography.bodyNormal14,
    )
}

@Preview
@Composable
fun PreviewHomeShotCountText() {
    TwoTooTheme {
        HomeShotCountText(homeShotCountTextData = HomeShotCountTextData())
    }
}

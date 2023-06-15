package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeShotCountTextUiModel

/**
 * @Created by 김현국 2023/06/04
 */

@Composable
fun HomeShotCountText(
    homeShotCountTextUiModel: HomeShotCountTextUiModel,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "콕 찌르기 (${homeShotCountTextUiModel.count}/5)",
        color = TwoTooTheme.color.mainBrown,
        style = TwoTooTheme.typography.bodyNormal14,
    )
}

@Preview
@Composable
fun PreviewHomeShotCountText() {
    TwoTooTheme {
        HomeShotCountText(homeShotCountTextUiModel = HomeShotCountTextUiModel.default)
    }
}

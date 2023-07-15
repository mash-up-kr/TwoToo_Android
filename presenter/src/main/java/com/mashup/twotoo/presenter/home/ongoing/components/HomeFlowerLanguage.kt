package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel

@Composable
fun HomeFlowerLanguage(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            text = homeFlowerUiModel.flowerType.getFlowerName(context = context),
            style = TwoTooTheme.typography.headLineNormal24,
            color = TwoTooTheme.color.mainBrown,
        )
        Text(
            text = homeFlowerUiModel.flowerType.getFlowerLanguage(context = context),
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.mainBrown,
        )
    }
}

@Preview
@Composable
private fun PreviewHomeFlowerLanguage() {
    TwoTooTheme {
        HomeFlowerLanguage(homeFlowerUiModel = HomeFlowerUiModel.default)
    }
}

package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel

@Composable
fun HomeFlowerLanguage(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
    onClickFlowerTextBubble: (HomeFlowerUiModel) -> Unit = {},
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val bubbleWidth = 92.dp * screenWidth / 375
    val bubbleHeight = 48.dp * screenHeight / 812
    val areaMargin = 10.dp * screenHeight / 812
    val bottomMargin = 12.dp * screenHeight / 812

    ConstraintLayout(
        modifier = modifier.clickable {
            onClickFlowerTextBubble(homeFlowerUiModel)
        },
    ) {
        val (bubbleWrapper, textRow) = createRefs()
        TwoTooImageView(
            modifier = Modifier.width(bubbleWidth).height(bubbleHeight).constrainAs(bubbleWrapper) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            model = R.drawable.ic_flower_text_bubble_wrapper,
            previewPlaceholder = R.drawable.ic_flower_text_bubble_wrapper,
            contentScale = ContentScale.Fit,
        )
        Row(
            modifier = Modifier.padding(
                start = areaMargin,
                end = areaMargin,
                top = areaMargin,
                bottom = areaMargin + bottomMargin,
            ).constrainAs(textRow) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
            },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.homeCompleteFlowerBubbleText),
            )
            TwoTooImageView(
                modifier = Modifier.size(18.dp),
                model = R.drawable.ic_flower_text_bubble_textflower,
                previewPlaceholder = R.drawable.ic_flower_text_bubble_textflower,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeFlowerLanguage() {
    TwoTooTheme {
        HomeFlowerLanguage(
            modifier = Modifier.width(92.dp).height(48.dp),
            homeFlowerUiModel = HomeFlowerUiModel.default,
        )
    }
}

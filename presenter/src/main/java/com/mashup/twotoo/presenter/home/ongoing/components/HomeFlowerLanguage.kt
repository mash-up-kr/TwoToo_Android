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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage

@Composable
fun HomeFlowerLanguage(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
    onClickFlowerTextBubble: (FlowerName) -> Unit = {},
) {
    val growType = (homeFlowerUiModel.flowerType as Flower).growType
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val bubbleWidth = if (growType.isSuccess()) 92.dp else 118.dp * screenWidth / 375
    val bubbleHeight = if (growType.isSuccess()) 48.dp else 63.dp * screenHeight / 812
    val areaMargin = 10.dp * screenHeight / 812
    val bottomMargin = 12.dp * screenHeight / 812

    ConstraintLayout(
        modifier = modifier.clickable {
            onClickFlowerTextBubble(homeFlowerUiModel.flowerType.flowerName)
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
            model = if (growType.isSuccess()) R.drawable.ic_flower_text_bubble_wrapper else R.drawable.ic_cheer_bubble_wrapper,
            previewPlaceholder = if (growType.isSuccess()) R.drawable.ic_flower_text_bubble_wrapper else R.drawable.ic_cheer_bubble_wrapper,
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
            if (growType.isSuccess()) {
                Text(
                    text = stringResource(id = R.string.homeCompleteFlowerBubbleSuccessText),
                    style = TwoTooTheme.typography.bodyNormal16,
                    color = TwoTooTheme.color.mainBrown,
                )
                TwoTooImageView(
                    modifier = Modifier.size(18.dp),
                    model = R.drawable.ic_flower_text_bubble_textflower,
                    previewPlaceholder = R.drawable.ic_flower_text_bubble_textflower,
                )
            } else {
                Text(
                    text = stringResource(id = R.string.homeCompleteFlowerBubbleFaileText),
                    style = TwoTooTheme.typography.bodyNormal16,
                    color = TwoTooTheme.color.gray600,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

private fun Stage.isSuccess(): Boolean {
    return this >= Stage.Fourth
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

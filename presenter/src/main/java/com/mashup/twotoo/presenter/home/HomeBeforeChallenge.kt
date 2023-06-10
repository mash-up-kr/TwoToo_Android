package com.mashup.twotoo.presenter.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.StateTitleUiModel

/**
 * @Created by 김현국 2023/06/10
 */

@Composable
fun HomeBeforeChallenge(
    beforeChallengeUiModel: BeforeChallengeUiModel,
    modifier: Modifier = Modifier,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (homeGoalCount, homeBeforeChallengeTitle, homeBeforeChallengeImage, textButton) = createRefs()
        HomeGoalCount(
            modifier = Modifier.constrainAs(homeGoalCount) {
                top.linkTo(parent.top, margin = 11.dp)
                end.linkTo(parent.end, margin = 22.dp)
            },
            homeGoalCountUiModel = beforeChallengeUiModel.homeGoalCountUiModel,
        )
        HomeBeforeChallengeTitle(
            modifier = Modifier.constrainAs(homeBeforeChallengeTitle) {
                top.linkTo(parent.top, margin = screenHeight * 0.33f.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            stateTitle = beforeChallengeUiModel.stateTitleUiModel,
        )
        HomeBeforeChallengeImage(
            modifier = Modifier
                .constrainAs(homeBeforeChallengeImage) {
                    top.linkTo(homeBeforeChallengeTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.width(93.dp)
                .height(109.dp),
            stateImage = beforeChallengeUiModel.stateImage,
        )
        TwoTooTextButton(
            modifier = Modifier.width(177.dp).height(57.dp).constrainAs(textButton) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 51.dp)
            },
            text = stringResource(id = beforeChallengeUiModel.buttonText),
        )
    }
}

@Composable
fun HomeBeforeChallengeTitle(
    stateTitle: StateTitleUiModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = stateTitle.title),
            style = TwoTooTheme.typography.headLineNormal28,
            color = TwoTooTheme.color.mainBrown,
            textAlign = TextAlign.Center,
        )
        stateTitle.subTitle?.let {
            Text(
                text = stringResource(id = stateTitle.subTitle),
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.gray600,
            )
        }
    }
}

@Composable
fun HomeBeforeChallengeImage(
    @DrawableRes stateImage: Int,
    modifier: Modifier = Modifier,
) {
    TwoTooImageView(
        modifier = modifier,
        model = stateImage,
        previewPlaceholder = stateImage,
    )
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewHomeBeforeChallenge() {
    TwoTooTheme {
        HomeBeforeChallenge(
            modifier = Modifier.fillMaxSize(),
            beforeChallengeUiModel = BeforeChallengeUiModel.default,
        )
    }
}

package com.mashup.twotoo.presenter.home.ongoing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.TwoTooGoalAchievementProgressbar
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.ongoing.components.HomeBeeButton
import com.mashup.twotoo.presenter.home.ongoing.components.HomeFlowerMeAndPartner
import com.mashup.twotoo.presenter.home.ongoing.components.HomeGoalCount
import com.mashup.twotoo.presenter.home.ongoing.components.HomeGoalField
import com.mashup.twotoo.presenter.home.ongoing.components.HomeShotCountText

/**
 * @Created by 김현국 2023/06/11
 */

@Composable
fun HomeOngoingChallenge(
    navigateToHistory: () -> Unit,
    onBeeButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    ongoingChallengeUiModel: OngoingChallengeUiModel = OngoingChallengeUiModel.default,
    onCommit: () -> Unit = {},
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            homeGoalField, goalAchievement, goalCount,
            homeBackground, beeButton, shotCount, homeFlower,
        ) = createRefs()

        TwoTooImageView(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.33f).constrainAs(homeBackground) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            previewPlaceholder = R.drawable.image_home_background,
            model = R.drawable.image_home_background,
            contentScale = ContentScale.FillBounds,
        )

        HomeGoalField(
            modifier = Modifier.constrainAs(homeGoalField) {
                top.linkTo(parent.top, margin = 11.dp)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            }.clickable { navigateToHistory() },
            homeGoalFieldUiModel = ongoingChallengeUiModel.homeGoalFieldUiModel,
        )
        TwoTooGoalAchievementProgressbar(
            modifier = Modifier.width(210.dp).height(59.dp).constrainAs(goalAchievement) {
                start.linkTo(homeGoalField.start)
                top.linkTo(homeGoalField.bottom)
            }.background(color = Color.White, shape = RoundedCornerShape(15.dp)),
            homeGoalAchievePartnerAndMeUiModel = ongoingChallengeUiModel.homeGoalAchievePartnerAndMeUiModel,
        )
        HomeGoalCount(
            modifier = Modifier.constrainAs(goalCount) {
                end.linkTo(homeGoalField.end)
                top.linkTo(goalAchievement.top)
                bottom.linkTo(goalAchievement.bottom)
            },
            homeGoalCountUiModel = ongoingChallengeUiModel.homeGoalCountUiModel,
        )

        val barrier = createTopBarrier(homeBackground, margin = 60.dp)
        HomeFlowerMeAndPartner(
            modifier = Modifier.fillMaxWidth().constrainAs(homeFlower) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(barrier)
            },
            onCommit = onCommit,
            homeChallengeStateUiModel = ongoingChallengeUiModel.homeChallengeStateUiModel,
        )

        HomeBeeButton(
            modifier = Modifier.constrainAs(beeButton) {
                top.linkTo(homeBackground.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }.clickable {
                onBeeButtonClick()
            },
        )
        HomeShotCountText(
            modifier = Modifier.constrainAs(shotCount) {
                top.linkTo(beeButton.bottom, margin = 10.19.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            homeShotCountTextUiModel = ongoingChallengeUiModel.homeShotCountTextUiModel,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFirstChallengeHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallenge,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthOnlyPartnerHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyPartner,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthOnlyMeHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyMe,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthBothHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDoNotAuthBothHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.doNotAuthBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDoNotCheerBothChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.doNotCheerBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCheerOnlyMeChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.cheerOnlyMe,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCheerOnlyPartnerChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.cheerOnlyPartner,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCheerBothChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = {},
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.cheerBoth,
                ),
            ),
        )
    }
}

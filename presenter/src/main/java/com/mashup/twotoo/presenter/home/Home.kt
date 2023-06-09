package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeFlower
import com.mashup.twotoo.presenter.home.model.HomeGoalAchieveData
import com.mashup.twotoo.presenter.home.model.HomeGoalCountData
import com.mashup.twotoo.presenter.home.model.HomeGoalFieldData
import com.mashup.twotoo.presenter.home.model.HomeModel
import com.mashup.twotoo.presenter.home.model.HomeShotCountTextData
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER

@Composable
fun HomeRoute() {
    HomeScreen(
        onBeeButtonClick = {},
    )
}

@Composable
fun HomeScreen(
    onBeeButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    homeModel: HomeModel = HomeModel(),
) {
    Box(modifier = modifier.fillMaxSize()) {
        TwoTooImageView(
            modifier = Modifier.fillMaxSize(),
            model = R.drawable.image_background,
            previewPlaceholder = R.drawable.image_background,
        )

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (
                topBar, homeGoalField, goalAchievement, goalCount,
                homeBackground, beeButton, shotCount, homeFlower,
            ) = createRefs()

            TwoTooMainToolbar(
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                onClickHelpIcon = {},
            )

            HomeGoalField(
                modifier = Modifier.constrainAs(homeGoalField) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    width = Dimension.fillToConstraints
                },
                homeGoalFieldData = HomeGoalFieldData(),
            )
            HomeGoalAchievement(
                modifier = Modifier.width(210.dp).height(59.dp).constrainAs(goalAchievement) {
                    start.linkTo(homeGoalField.start)
                    top.linkTo(homeGoalField.bottom)
                }.background(color = Color.White, shape = RoundedCornerShape(15.dp)),
                goalAchieveDataList = listOf(
                    HomeGoalAchieveData(name = "공주", type = PARTNER, progress = 0.7f),
                    HomeGoalAchieveData(name = "나", type = ME, progress = 0.6f),
                ),
            )
            HomeGoalCount(
                modifier = Modifier.constrainAs(goalCount) {
                    end.linkTo(homeGoalField.end)
                    top.linkTo(goalAchievement.top)
                    bottom.linkTo(goalAchievement.bottom)
                },
                homeGoalCountData = HomeGoalCountData(),
            )
            TwoTooImageView(
                modifier = Modifier.fillMaxWidth().height(244.dp).constrainAs(homeBackground) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                previewPlaceholder = R.drawable.image_home_background,
                model = R.drawable.image_home_background,
            )
            HomeFlowerMeAndPartner(
                modifier = Modifier.constrainAs(homeFlower) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(beeButton.top, margin = 26.29.dp)
                },
                meAndPartner = homeModel.homeFlower,
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
                homeShotCountTextData = HomeShotCountTextData(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFirstChallengeHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
            homeModel = HomeModel().copy(
                homeFlower = HomeFlower.firstChallengeList,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthOnlyPartnerHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
            homeModel = HomeModel().copy(
                homeFlower = HomeFlower.authOnlyPartnerList,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthOnlyMeHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
            homeModel = HomeModel().copy(
                homeFlower = HomeFlower.authOnlyMeList,

            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthBothHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
            homeModel = HomeModel().copy(
                homeFlower = HomeFlower.authBoth,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDoNotAuthBothHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
            homeModel = HomeModel().copy(
                homeFlower = HomeFlower.doNotAuthBoth,
            ),
        )
    }
}

package com.mashup.twotoo.presenter.home.ongoingChallenge.auth.firstCreateChallenge

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.google.common.truth.Truth
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.HomeScreen
import com.mashup.twotoo.presenter.home.model.ChallengeState
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import org.junit.Rule
import org.junit.Test


class HomeOngoingFirstChallengeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Test
    fun 챌린지_생성후_FirstCreateChallenge일때() {
        val challengeStateTypeUiModel = OngoingChallengeUiModel.default.copy(
            homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                challengeState = ChallengeState.Auth,
                challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallenge,
            ),
        )

        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = challengeStateTypeUiModel,
                )
            }
        }

        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeFirstChallengeHint),
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeWaterImage),
        ).assertIsDisplayed()

        (challengeStateTypeUiModel.homeChallengeStateUiModel.challengeStateUiModel as HomeFlowerPartnerAndMeUiModel).also {
            Truth.assertThat(
                it.partner.flowerType.getFlowerImage(context),
            ).isEqualTo(R.drawable.img_home_zero_stage_partner)

            Truth.assertThat(
                it.me.flowerType.getFlowerImage(context),
            ).isEqualTo(R.drawable.img_home_zero_stage_me)
        }
    }
}

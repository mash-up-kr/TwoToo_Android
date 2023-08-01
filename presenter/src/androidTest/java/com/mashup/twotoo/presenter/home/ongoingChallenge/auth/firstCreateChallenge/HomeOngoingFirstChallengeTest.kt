package com.mashup.twotoo.presenter.home.ongoingChallenge.auth.firstCreateChallenge

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.google.common.truth.Truth
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.HomeScreen
import com.mashup.twotoo.presenter.home.model.AuthType
import com.mashup.twotoo.presenter.home.model.ChallengeState
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage
import org.junit.Rule
import org.junit.Test

class HomeOngoingFirstChallengeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private var screenHeight: Int = 0
    private var screenWidth: Int = 0

    @Test
    fun 챌린지_생성후_FirstCreateChallenge일때() {
        val challengeStateTypeUiModel = HomeStateUiModel(
            challengeStateUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeState = ChallengeState.Auth,
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallenge,
                ),
            ),
        )

        composeTestRule.setContent {
            context = LocalContext.current
            screenHeight = LocalConfiguration.current.screenHeightDp
            screenWidth = LocalConfiguration.current.screenWidthDp
            TwoTooTheme {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {},
                )
            }
        }

        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeFirstChallengeHint),
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeWaterImage),
        ).assertIsDisplayed()

        (challengeStateTypeUiModel.challengeStateUiModel as OngoingChallengeUiModel).homeChallengeStateUiModel
            .also {
                Truth.assertThat(
                    (it.challengeStateUiModel as HomeFlowerPartnerAndMeUiModel).partner.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
                ).isEqualTo(R.drawable.img_home_zero_stage_partner)

                Truth.assertThat(
                    (it.challengeStateUiModel as HomeFlowerPartnerAndMeUiModel).me.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
                ).isEqualTo(R.drawable.img_home_zero_stage_me)
            }
    }

    @Test
    fun 챌린지_생성후_FirstCreateChallenge일때_상대방이_인증했을_경우() {
        val challengeStateTypeUiModel = HomeStateUiModel(
            challengeStateUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeState = ChallengeState.Auth,
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallengeButAuthOnlyPartner.copy(
                        authType = AuthType.FirstCreateChallengeButAuthOnlyPartner,
                        partner = HomeFlowerUiModel.partner.copy(
                            flowerType = Flower(
                                flowerName = FlowerName.Tulip,
                                userType = UserType.PARTNER,
                                growType = Stage.First,
                            ),
                        ),
                    ),
                ),
            ),
        )

        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {},
                )
            }
        }
        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeAuthPartnerText),
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeFirstChallengeHint),
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            context.getString(R.string.homeOngoingChallengeWaterImage),
        ).assertIsDisplayed()

        (challengeStateTypeUiModel.challengeStateUiModel as OngoingChallengeUiModel).homeChallengeStateUiModel.also {
            Truth.assertThat(
                (it.challengeStateUiModel as HomeFlowerPartnerAndMeUiModel).partner.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
            ).isEqualTo(R.drawable.img_home_first_stage_partner)

            Truth.assertThat(
                (it.challengeStateUiModel as HomeFlowerPartnerAndMeUiModel).me.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
            ).isEqualTo(R.drawable.img_home_zero_stage_me)
        }
    }
}

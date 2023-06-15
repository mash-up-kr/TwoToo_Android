package com.mashup.twotoo.presenter.home

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.google.common.truth.Truth.assertThat
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import org.junit.Rule
import org.junit.Test

/**
 * @Created by 김현국 2023/06/12
 */

class HomeKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Test
    fun 챌린지_생성_전_EmptyModel일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.empty
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            // Title 텍스트가 맞는지 검증
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeEmpty),
            )

            // Image가 Empty이미지 인지 검증
            assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.image_challenge_empty)

            // Button 텍스트가 맞는지 검증
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeEmptyButtonText),
            )
        }
    }

    @Test
    fun 챌린지_생성_전_State가_Request일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.request
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }

        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeRequest),
            )
            assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.image_challenge_empty)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeRequestButtonText),
            )
        }
    }

    @Test
    fun 챌린지_생성_전_State가_Response일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.response
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }

        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeResponse),
            )

            assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.image_challenge_response)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeResponseButtonText),
            )
        }
    }

    @Test
    fun 챌린지_생성_전_State가_Wait일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.wait
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeWait),
            )

            assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.image_challenge_empty)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeSubTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeWaitSubTitle),
            )

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeWaitButtonText),
            )
        }
    }

    @Test
    fun 챌린지_생성_전_State가_termination일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.termination
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeTermination),
            )

            assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.image_challenge_empty)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeTerminationButtonText),
            )
        }
    }

    @Test
    fun 테스트_생성_후_State가_FirstCreateChallenge일때() {
        val challengeStateTypeUiModel = OngoingChallengeUiModel.default.copy(
            homeFlowerUiModels = HomeFlowerPartnerAndMeUiModel.firstChallenge,
        )
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeFirstChallengeHint),
            ).assertIsDisplayed()

            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeWaterImage),
            ).assertIsDisplayed()

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.partner.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.me.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)
        }
    }

    @Test
    fun 테스트_생성_후_State가_AuthOnlyPartner일때() {
        val challengeStateTypeUiModel = OngoingChallengeUiModel.default.copy(
            homeFlowerUiModels = HomeFlowerPartnerAndMeUiModel.authOnlyPartner,
        )
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeFirstChallengeHint),
            ).assertDoesNotExist()

            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeWaterImage),
            ).assertIsDisplayed()

            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeAuthPartnerText),
            ).assertIsDisplayed()

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.partner.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.me.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)
        }
    }

    @Test
    fun 테스트_생성_후_State가_AuthOnlyMe일때() {
        val challengeStateTypeUiModel = OngoingChallengeUiModel.default.copy(
            homeFlowerUiModels = HomeFlowerPartnerAndMeUiModel.authOnlyMe,
        )
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeFirstChallengeHint),
            ).assertDoesNotExist()

            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeWaterImage),
            ).assertDoesNotExist()

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.partner.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.me.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)
        }
    }

    @Test
    fun 테스트_생성_후_State가_AuthBoth일때() {
        val challengeStateTypeUiModel = OngoingChallengeUiModel.default.copy(
            homeFlowerUiModels = HomeFlowerPartnerAndMeUiModel.authBoth,
        )
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }

        with(context) {
            composeTestRule.onNodeWithText(
                getString(R.string.homeFirstCreatedChallenge),
            ).assertDoesNotExist()

            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeWaterImage),
            ).assertDoesNotExist()

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.partner.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.me.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)
        }
    }

    @Test
    fun 테스트_생성_후_State가_DoNotAuthBoth일때() {
        val challengeStateTypeUiModel = OngoingChallengeUiModel.default.copy(
            homeFlowerUiModels = HomeFlowerPartnerAndMeUiModel.doNotAuthBoth,
        )
        composeTestRule.setContent {
            context = LocalContext.current
            HomeScreen(
                challengeStateTypeUiModel = challengeStateTypeUiModel,
            )
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeFirstChallengeHint),
            ).assertDoesNotExist()

            composeTestRule.onNodeWithTag(
                getString(R.string.homeOngoingChallengeWaterImage),
            ).assertIsDisplayed()

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.partner.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)

            assertThat(
                challengeStateTypeUiModel
                    .homeFlowerUiModels.me.growType.growImage,
            ).isEqualTo(R.drawable.ic_seed)
        }
    }
}

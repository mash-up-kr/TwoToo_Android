package com.mashup.twotoo.presenter.home.beforeChallenge

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.google.common.truth.Truth
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.HomeScreen
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import org.junit.Rule
import org.junit.Test



class HomeBeforeChallengeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Test
    fun 챌린지_생성전_Empty일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.empty
        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {}
                )
            }
        }
        with(context) {
            // Title 텍스트가 맞는지 검증
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeEmpty),
            )

            // Image가 Empty이미지 인지 검증
            Truth.assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.img_challenge_empty)

            // Button 텍스트가 맞는지 검증
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeEmptyButtonText),
            )
        }
    }

    /**
     * 챌린지 요청을 보냈을 때
     */
    @Test
    fun 챌린지_생성_전_State가_Request일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.request
        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {}
                )
            }
        }

        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeRequest),
            )
            Truth.assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.img_challenge_request)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeRequestButtonText),
            )
        }
    }

    /**
     * 챌린지 요청이 왔을때
     */
    @Test
    fun 챌린지_생성_전_State가_Response일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.response
        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {}
                )
            }
        }

        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeResponse),
            )

            Truth.assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.img_challenge_response)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeResponseButtonText),
            )
        }
    }

    /**
     * 챌린지 시작일 대기중일 때
     */
    @Test
    fun 챌린지_생성_전_State가_Wait일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.wait
        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {}
                )
            }
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeWait),
            )

            Truth.assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.img_challenge_wait)

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

    /**
     * 챌린지가 만료 되었을 때
     */
    @Test
    fun 챌린지_생성_전_State가_termination일때() {
        val challengeStateTypeUiModel = BeforeChallengeUiModel.termination
        composeTestRule.setContent {
            context = LocalContext.current
            TwoTooTheme {
                HomeScreen(
                    state = challengeStateTypeUiModel,
                    navigateToGuide = {}
                )
            }
        }
        with(context) {
            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeTitle),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeTermination),
            )

            Truth.assertThat(
                challengeStateTypeUiModel.stateImage,
            ).isEqualTo(R.drawable.img_challenge_termination)

            composeTestRule.onNodeWithTag(
                getString(R.string.homeBeforeChallengeButton),
            ).assertTextEquals(
                getString(R.string.homeBeforeChallengeTerminationButtonText),
            )
        }
    }
}

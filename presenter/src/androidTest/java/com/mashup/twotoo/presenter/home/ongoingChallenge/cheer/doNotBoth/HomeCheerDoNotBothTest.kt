package com.mashup.twotoo.presenter.home.ongoingChallenge.cheer.doNotBoth

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.google.common.truth.Truth
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.HomeScreen
import com.mashup.twotoo.presenter.home.model.CheerState
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.model.Stage
import org.junit.Rule
import org.junit.Test

class HomeCheerDoNotBothTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private var screenHeight: Int = 0
    private var screenWidth: Int = 0

    /**
     * 상대방과 내가 둘다 응원을 하지 않았을 때 상태입니다.
     *
     * * 첫번째 챌린지 일때 텍스트가 보이지 않아야합니다.
     * * 인증을 하지 않았을 때 물뿌리개 이미지가 보이지 않아야합니다.
     * * 상대방이 인증했을때 텍스트가 보이지 않아야합니다.
     * * 나의 텍스트 풍선이 보이지 않아야합니다.
     * * 파트너 텍스트 풍선이 보이지 않아야합니다.
     * * 나의 응원 전 말풍선은 보여야합니다.
     * * 파트너 응원 전 말풍선은 보여야합니다.
     */

    private fun testComposableWithTag() = with(context) {
        composeTestRule.onNodeWithTag(
            getString(R.string.homeOngoingChallengeFirstChallengeHint),
        ).assertDoesNotExist()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeOngoingChallengeWaterImage),
        ).assertDoesNotExist()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeOngoingChallengeAuthPartnerText),
            useUnmergedTree = true,
        ).assertDoesNotExist()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeCheerChallengeMeBubble),
        ).assertDoesNotExist()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeCheerChallengeMeBeforeCheerBubble),
        ).assertExists()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeCheerChallengePartnerBubble),
        ).assertDoesNotExist()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeCheerChallengePartnerBeforeCheerBubble),
        ).assertExists()
    }

    /**
     * 모든 테스트의 이미지는 Tulip으로 진행합니다.
     */
    private fun testPartnerAndMeImageTest(
        challengeStateTypeUiModel: OngoingChallengeUiModel,
        meStage: Stage,
        partnerStage: Stage,
    ) {
        (challengeStateTypeUiModel.homeChallengeStateUiModel.challengeStateUiModel as HomeCheerUiModel).also {
            Truth.assertThat(
                it.partner.homeFlowerUiModel.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
            ).isEqualTo(
                when (partnerStage) {
                    Stage.Zero -> R.drawable.img_home_zero_stage_partner
                    Stage.First -> R.drawable.img_home_first_stage_partner
                    Stage.Second -> R.drawable.img_home_second_stage_partner
                    Stage.Third -> R.drawable.img_home_third_stage_partner
                    Stage.Fourth -> R.drawable.img_home_fourth_stage_tulip_partner
                    Stage.Fifth -> R.drawable.img_home_fifth_stage_tulip_partner
                },
            )

            Truth.assertThat(
                it.me.homeFlowerUiModel.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
            ).isEqualTo(
                when (meStage) {
                    Stage.Zero -> R.drawable.img_home_zero_stage_me
                    Stage.First -> R.drawable.img_home_first_stage_me
                    Stage.Second -> R.drawable.img_home_second_stage_me
                    Stage.Third -> R.drawable.img_home_third_stage_me
                    Stage.Fourth -> R.drawable.img_home_fourth_stage_tulip_me
                    Stage.Fifth -> R.drawable.img_home_fifth_stage_tulip_me
                },
            )
        }
    }

    private fun getUiModel(
        partnerStage: Stage,
        meStage: Stage,
    ): OngoingChallengeUiModel {
        return OngoingChallengeUiModel.cheer.copy(
            homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                challengeStateUiModel = HomeCheerUiModel.doNotCheerBoth.copy(
                    cheerState = CheerState.DoNotCheerBoth,
                    partner = CheerWithFlower.partnerNotYet.copy(
                        homeFlowerUiModel = when (partnerStage) {
                            Stage.Zero -> HomeFlowerUiModel.partnerZeroState
                            Stage.First -> HomeFlowerUiModel.partnerFirstState
                            Stage.Second -> HomeFlowerUiModel.partnerSecondState
                            Stage.Third -> HomeFlowerUiModel.partnerThirdState
                            Stage.Fourth -> HomeFlowerUiModel.partnerFourthState
                            Stage.Fifth -> HomeFlowerUiModel.partnerFifthState
                        },
                    ),
                    me = CheerWithFlower.meNotYet.copy(
                        homeFlowerUiModel = when (meStage) {
                            Stage.Zero -> HomeFlowerUiModel.meZeroState
                            Stage.First -> HomeFlowerUiModel.meFirstState
                            Stage.Second -> HomeFlowerUiModel.meSecondState
                            Stage.Third -> HomeFlowerUiModel.meThirdState
                            Stage.Fourth -> HomeFlowerUiModel.meFourthState
                            Stage.Fifth -> HomeFlowerUiModel.meFifthState
                        },
                    ),
                ),
            ),
        )
    }
    private fun createTest(
        partnerStage: Stage,
        meStage: Stage,
    ) {
        val challengeStateTypeUiModel = HomeStateUiModel(
            challengeStateUiModel = getUiModel(
                partnerStage = partnerStage,
                meStage = meStage,
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
                    navigateToGuide = {}
                )
            }
        }

        testComposableWithTag()
        testPartnerAndMeImageTest(
            challengeStateTypeUiModel = challengeStateTypeUiModel.challengeStateUiModel as OngoingChallengeUiModel,
            partnerStage = partnerStage,
            meStage = meStage,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_0단계_me_0단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_0단계_me_1단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_0단계_me_2단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_0단계_me_3단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_0단계_me_4단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_0단계_me_5단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_1단계_me_0단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_1단계_me_1단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_1단계_me_2단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_1단계_me_3단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_1단계_me_4단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_1단계_me_5단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_2단계_me_0단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_2단계_me_1단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_2단계_me_2단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_2단계_me_3단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_2단계_me_4단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_2단계_me_5단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_3단계_me_0단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_3단계_me_1단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_3단계_me_2단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_3단계_me_3단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_3단계_me_4단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_3단계_me_5단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_4단계_me_0단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_4단계_me_1단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_4단계_me_2단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_4단계_me_3단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_4단계_me_4단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_4단계_me_5단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_5단계_me_0단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_5단계_me_1단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_5단계_me_2단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_5단계_me_3단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_5단계_me_4단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_CheerDoNotBoth일때_partner_5단계_me_5단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Fifth,
        )
    }
}

package com.mashup.twotoo.presenter.home.ongoingChallenge.auth.onlyPartner

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
import com.mashup.twotoo.presenter.home.model.AuthType
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.model.Stage
import org.junit.Rule
import org.junit.Test

class HomeOngoingAuthOnlyPartnerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private var screenHeight: Int = 0
    private var screenWidth: Int = 0

    /**
     * * 첫번째 챌린지 일때 텍스트 테스트
     * * 인증을 하지 않았을때 물뿌리개 이미지 테스트
     * * 상대방이 인증했을때 텍스트 테스트
     */
    private fun testComposableWithTag() = with(context) {
        composeTestRule.onNodeWithTag(
            getString(R.string.homeOngoingChallengeFirstChallengeHint),
        ).assertDoesNotExist()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeOngoingChallengeWaterImage),
        ).assertExists()

        composeTestRule.onNodeWithTag(
            getString(R.string.homeOngoingChallengeAuthPartnerText),
            useUnmergedTree = true,
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
        (challengeStateTypeUiModel.homeChallengeStateUiModel.challengeStateUiModel as HomeFlowerPartnerAndMeUiModel).also {
            Truth.assertThat(
                it.partner.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
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
                it.me.flowerType.getFlowerImage(context, screenWidth, screenHeight).image,
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
        return OngoingChallengeUiModel.default.copy(
            homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyPartner.copy(
                    authType = AuthType.AuthOnlyPartner,
                    partner = when (partnerStage) {
                        Stage.Zero -> HomeFlowerUiModel.partnerZeroState
                        Stage.First -> HomeFlowerUiModel.partnerFirstState
                        Stage.Second -> HomeFlowerUiModel.partnerSecondState
                        Stage.Third -> HomeFlowerUiModel.partnerThirdState
                        Stage.Fourth -> HomeFlowerUiModel.partnerFourthState
                        Stage.Fifth -> HomeFlowerUiModel.partnerFifthState
                    },
                    me = when (meStage) {
                        Stage.Zero -> HomeFlowerUiModel.meZeroState
                        Stage.First -> HomeFlowerUiModel.meFirstState
                        Stage.Second -> HomeFlowerUiModel.meSecondState
                        Stage.Third -> HomeFlowerUiModel.meThirdState
                        Stage.Fourth -> HomeFlowerUiModel.meFourthState
                        Stage.Fifth -> HomeFlowerUiModel.meFifthState
                    },
                ),
            ),
        )
    }

    private fun createTest(
        partnerStage: Stage,
        meStage: Stage,
    ) {
        val challengeStateTypeUiModel = getUiModel(
            partnerStage = partnerStage,
            meStage = meStage,
        )
        composeTestRule.setContent {
            context = LocalContext.current
            screenHeight = LocalConfiguration.current.screenHeightDp
            screenWidth = LocalConfiguration.current.screenWidthDp
            TwoTooTheme {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = challengeStateTypeUiModel,
                )
            }
        }

        testComposableWithTag()
        testPartnerAndMeImageTest(
            challengeStateTypeUiModel = challengeStateTypeUiModel,
            partnerStage = partnerStage,
            meStage = meStage,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_0단계_me_0단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_0단계_me_1단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_0단계_me_2단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_0단계_me_3단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_0단계_me_4단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_0단계_me_5단계() {
        createTest(
            partnerStage = Stage.Zero,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_1단계_me_0단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_1단계_me_1단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_1단계_me_2단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_1단계_me_3단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_1단계_me_4단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_1단계_me_5단계() {
        createTest(
            partnerStage = Stage.First,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_2단계_me_0단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_2단계_me_1단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_2단계_me_2단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_2단계_me_3단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_2단계_me_4단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_2단계_me_5단계() {
        createTest(
            partnerStage = Stage.Second,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_3단계_me_0단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_3단계_me_1단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_3단계_me_2단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_3단계_me_3단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_3단계_me_4단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_3단계_me_5단계() {
        createTest(
            partnerStage = Stage.Third,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_4단계_me_0단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_4단계_me_1단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_4단계_me_2단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_4단계_me_3단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_4단계_me_4단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_4단계_me_5단계() {
        createTest(
            partnerStage = Stage.Fourth,
            meStage = Stage.Fifth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_5단계_me_0단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Zero,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_5단계_me_1단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.First,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_5단계_me_2단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Second,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_5단계_me_3단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Third,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_5단계_me_4단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Fourth,
        )
    }

    @Test
    fun 챌린지_생성후_AuthOnlyPartner일때_partner_5단계_me_5단계() {
        createTest(
            partnerStage = Stage.Fifth,
            meStage = Stage.Fifth,
        )
    }
}

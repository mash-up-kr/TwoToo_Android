package com.mashup.twotoo.presenter.home.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.twotoo.presenter.home.model.AuthType
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.CheerState
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.model.Stage

class PreviewBeforeHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        beforeEmptyChallenge,
        beforeRequestChallenge,
        beforeResponseChallenge,
        beforeWaitChallenge,
        beforeTerminationChallenge,
    ).asSequence()

    companion object {
        val beforeEmptyChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.empty,
        )
        val beforeRequestChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.request,
        )
        val beforeResponseChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.response,
        )
        val beforeWaitChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.wait,
        )
        val beforeTerminationChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.termination,
        )
    }
}

class PreviewOngoingHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        ongoingAuthChallengeFirstState,
        ongoingAuthChallengeAuthBothStageFirst,
        ongoingAuthChallengeAuthBothStageSecond,
        ongoingAuthChallengeAuthBothStageThird,
        ongoingAuthChallengeAuthBothStageFourth,
        ongoingAuthChallengeAuthBothStageFifth,
    ).asSequence()

    companion object {

        val ongoingAuthChallengeFirstState = HomeStateUiModel(
            challengeStateUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallenge,
                ),
            ),
        )

        val ongoingAuthChallengeAuthBothStageFirst = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.First, Stage.First),
        )
        val ongoingAuthChallengeAuthBothStageSecond = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Second, Stage.Second),
        )
        val ongoingAuthChallengeAuthBothStageThird = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Third, Stage.Third),
        )
        val ongoingAuthChallengeAuthBothStageFourth = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fourth, Stage.Fourth),
        )
        val ongoingAuthChallengeAuthBothStageFifth = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth),
        )
    }
}

class PreviewCheerHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        ongoingCheerChallengeFirstStage,
        ongoingCheerChallengeSecondStage,
        ongoingCheerChallengeThirdStage,
        ongoingCheerChallengeFourthStage,
        ongoingCheerChallengeFifthStage,
    ).asSequence()

    companion object {

        val ongoingCheerChallengeFirstStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.First, Stage.First),
        )
        val ongoingCheerChallengeSecondStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Second, Stage.Second),
        )
        val ongoingCheerChallengeThirdStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Third, Stage.Third),
        )
        val ongoingCheerChallengeFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth),
        )
        val ongoingCheerChallengeFifthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fifth, Stage.Fifth),
        )
    }
}

private fun getAuthUiModel(
    partnerStage: Stage,
    meStage: Stage,
): OngoingChallengeUiModel {
    return OngoingChallengeUiModel.default.copy(
        homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
            challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth.copy(
                authType = AuthType.AuthBoth,
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

private fun getCheerUiModel(
    partnerStage: Stage,
    meStage: Stage,
): OngoingChallengeUiModel {
    return OngoingChallengeUiModel.cheer.copy(
        homeChallengeStateUiModel = HomeChallengeStateUiModel.cheerBoth.copy(
            challengeStateUiModel = HomeCheerUiModel.cheerBoth.copy(
                cheerState = CheerState.CheerBoth,
                partner = CheerWithFlower.partnerNotEmpty.copy(
                    homeFlowerUiModel = when (partnerStage) {
                        Stage.Zero -> HomeFlowerUiModel.partnerZeroState
                        Stage.First -> HomeFlowerUiModel.partnerFirstState
                        Stage.Second -> HomeFlowerUiModel.partnerSecondState
                        Stage.Third -> HomeFlowerUiModel.partnerThirdState
                        Stage.Fourth -> HomeFlowerUiModel.partnerFourthState
                        Stage.Fifth -> HomeFlowerUiModel.partnerFifthState
                    },
                ),
                me = CheerWithFlower.meNotEmpty.copy(
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

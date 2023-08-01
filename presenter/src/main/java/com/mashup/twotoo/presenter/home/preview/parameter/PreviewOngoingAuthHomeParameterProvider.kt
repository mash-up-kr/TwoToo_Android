package com.mashup.twotoo.presenter.home.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.twotoo.presenter.home.model.AuthType
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.model.Stage

class PreviewOngoingAuthHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
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
            challengeStateUiModel = getAuthUiModel(Stage.Third, Stage.Third),
        )
        val ongoingAuthChallengeAuthBothStageFifth = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Third, Stage.Third),
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

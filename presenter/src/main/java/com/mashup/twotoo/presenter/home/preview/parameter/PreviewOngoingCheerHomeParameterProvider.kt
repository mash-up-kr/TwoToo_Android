package com.mashup.twotoo.presenter.home.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.twotoo.presenter.home.model.CheerState
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.model.Stage

class PreviewCheerHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        cheerOnlyPartnerChallengeFirstStage,
        cheerOnlyPartnerChallengeSecondStage,
        cheerOnlyPartnerChallengeThirdStage,
        ongoingCheerChallengeFirstStage,
        ongoingCheerChallengeSecondStage,
        ongoingCheerChallengeThirdStage,
    ).asSequence()

    companion object {

        val cheerOnlyPartnerChallengeFirstStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.First, Stage.First, cheerState = CheerState.CheerOnlyPartner),
        )
        val cheerOnlyPartnerChallengeSecondStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Second, Stage.Second, cheerState = CheerState.CheerOnlyPartner),
        )

        val cheerOnlyPartnerChallengeThirdStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Third, Stage.Third, cheerState = CheerState.CheerOnlyPartner),
        )

        val ongoingCheerChallengeFirstStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.First, Stage.First, cheerState = CheerState.CheerBoth),
        )
        val ongoingCheerChallengeSecondStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Second, Stage.Second, cheerState = CheerState.CheerBoth),
        )
        val ongoingCheerChallengeThirdStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Third, Stage.Third, cheerState = CheerState.CheerBoth),
        )
    }
}

private fun getCheerUiModel(
    partnerStage: Stage,
    meStage: Stage,
    cheerState: CheerState,
): OngoingChallengeUiModel {
    return OngoingChallengeUiModel.cheer.copy(
        homeChallengeStateUiModel = HomeChallengeStateUiModel.cheerBoth.copy(
            challengeStateUiModel = HomeCheerUiModel.cheerBoth.copy(
                cheerState = cheerState,
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
                me = if (cheerState == CheerState.CheerOnlyPartner) {
                    CheerWithFlower.meNotYet.copy(
                        homeFlowerUiModel = when (meStage) {
                            Stage.Zero -> HomeFlowerUiModel.meZeroState
                            Stage.First -> HomeFlowerUiModel.meFirstState
                            Stage.Second -> HomeFlowerUiModel.meSecondState
                            Stage.Third -> HomeFlowerUiModel.meThirdState
                            Stage.Fourth -> HomeFlowerUiModel.meFourthState
                            Stage.Fifth -> HomeFlowerUiModel.meFifthState
                        },
                    )
                } else {
                    CheerWithFlower.meNotEmpty.copy(
                        homeFlowerUiModel = when (meStage) {
                            Stage.Zero -> HomeFlowerUiModel.meZeroState
                            Stage.First -> HomeFlowerUiModel.meFirstState
                            Stage.Second -> HomeFlowerUiModel.meSecondState
                            Stage.Third -> HomeFlowerUiModel.meThirdState
                            Stage.Fourth -> HomeFlowerUiModel.meFourthState
                            Stage.Fifth -> HomeFlowerUiModel.meFifthState
                        },
                    )
                },
            ),
        ),
    )
}

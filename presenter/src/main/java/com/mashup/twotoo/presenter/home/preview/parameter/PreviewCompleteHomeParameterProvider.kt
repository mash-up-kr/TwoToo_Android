package com.mashup.twotoo.presenter.home.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.twotoo.presenter.home.model.AuthType
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage

class PreviewCompleteHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        completeZeroStageChallenge,
        completeZeroFifthStageChallenge,
        completeFirstStageChallenge,
        completeSecondStageChallenge,
        completeThirdStageChallenge,
        completeFourthStageChallenge,
        completeFifthStageChallenge,
        completeFifthStageRoseChallenge,
        completeFifthStageCottonChallenge,
        completeFifthStageFigChallenge,
        completeFifthStageChrysanthemumChallenge,
        completeFifthStageSunFlowerChallenge,
        completeFifthStageCamelliaChallenge,
        completeFifthStageDelphiniumChallenge,
    ).asSequence()

    companion object {

        val completeZeroStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Zero, Stage.Zero),
        )

        val completeZeroFifthStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Zero, Stage.Fifth, FlowerName.Camellia),
        )
        val completeFirstStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.First, Stage.First),
        )
        val completeSecondStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Second, Stage.Second),
        )
        val completeThirdStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Third, Stage.Third),
        )
        val completeFourthStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fourth, Stage.Fourth),
        )
        val completeFifthStageChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth),
        )
        val completeFifthStageRoseChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Rose),
        )
        val completeFifthStageCottonChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Cotton),
        )
        val completeFifthStageFigChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Fig),
        )
        val completeFifthStageChrysanthemumChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Chrysanthemum),
        )
        val completeFifthStageSunFlowerChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Sunflower),
        )
        val completeFifthStageCamelliaChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Camellia),
        )
        val completeFifthStageDelphiniumChallenge = HomeStateUiModel(
            challengeStateUiModel = getAuthUiModel(Stage.Fifth, Stage.Fifth, FlowerName.Delphinium),
        )
    }
}

private fun getAuthUiModel(
    partnerStage: Stage,
    meStage: Stage,
    flowerName: FlowerName = FlowerName.Tulip,
): OngoingChallengeUiModel {
    return OngoingChallengeUiModel.default.copy(
        homeChallengeStateUiModel = HomeChallengeStateUiModel.complete.copy(
            challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth.copy(
                authType = AuthType.AuthBoth,
                partner = when (partnerStage) {
                    Stage.Zero -> HomeFlowerUiModel.partnerZeroState
                    Stage.First -> HomeFlowerUiModel.partnerFirstState
                    Stage.Second -> HomeFlowerUiModel.partnerSecondState
                    Stage.Third -> HomeFlowerUiModel.partnerThirdState
                    Stage.Fourth -> HomeFlowerUiModel.partnerFourthState
                    Stage.Fifth -> HomeFlowerUiModel.partnerFifthState.copy(
                        flowerType = Flower(
                            flowerName = flowerName,
                            userType = UserType.PARTNER,
                            growType = Stage.Fifth,
                        ),
                    )
                },
                me = when (meStage) {
                    Stage.Zero -> HomeFlowerUiModel.meZeroState
                    Stage.First -> HomeFlowerUiModel.meFirstState
                    Stage.Second -> HomeFlowerUiModel.meSecondState
                    Stage.Third -> HomeFlowerUiModel.meThirdState
                    Stage.Fourth -> HomeFlowerUiModel.meFourthState
                    Stage.Fifth -> HomeFlowerUiModel.meFifthState.copy(
                        flowerType = Flower(
                            flowerName = flowerName,
                            userType = UserType.ME,
                            growType = Stage.Fifth,
                        ),
                    )
                },
            ),
        ),
    )
}

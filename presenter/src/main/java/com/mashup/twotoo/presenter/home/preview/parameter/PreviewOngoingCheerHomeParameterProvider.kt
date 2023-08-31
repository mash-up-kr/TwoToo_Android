package com.mashup.twotoo.presenter.home.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.twotoo.presenter.home.model.CheerState
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage

class PreviewCheerHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        cheerOnlyPartnerChallengeFirstStage,
        cheerOnlyPartnerChallengeSecondStage,
        cheerOnlyPartnerChallengeThirdStage,
        cheerOnlyPartnerChallengeFourthStage,
        cheerOnlyPartnerChallengeFifthStage,
        ongoingCheerChallengeFirstStage,
        ongoingCheerChallengeSecondStage,
        ongoingCheerChallengeThirdStage,
        ongoingCheerChallengeFourthStage,
        ongoingCheerChallengeRoseFourthStage,
        ongoingCheerChallengeCottonFourthStage,
        ongoingCheerChallengeFigFourthStage,
        ongoingCheerChallengeChrysanthemumFourthStage,
        ongoingCheerChallengeSunFlowerFourthStage,
        ongoingCheerChallengeCamelliaFourthStage,
        ongoingCheerChallengeDelphiniumFourthStage,
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

        val cheerOnlyPartnerChallengeFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth),
        )

        val cheerOnlyPartnerChallengeFifthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fifth, Stage.Fifth, cheerState = CheerState.CheerBoth),
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
        val ongoingCheerChallengeFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth),
        )
        val ongoingCheerChallengeRoseFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Rose),
        )
        val ongoingCheerChallengeCottonFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Cotton),
        )
        val ongoingCheerChallengeFigFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Fig),
        )
        val ongoingCheerChallengeChrysanthemumFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Chrysanthemum),
        )
        val ongoingCheerChallengeSunFlowerFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Sunflower),
        )
        val ongoingCheerChallengeCamelliaFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Camellia),
        )
        val ongoingCheerChallengeDelphiniumFourthStage = HomeStateUiModel(
            challengeStateUiModel = getCheerUiModel(Stage.Fourth, Stage.Fourth, cheerState = CheerState.CheerBoth, flowerName = FlowerName.Delphinium),
        )
    }
}

private fun getCheerUiModel(
    partnerStage: Stage,
    meStage: Stage,
    cheerState: CheerState,
    flowerName: FlowerName = FlowerName.Tulip,
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
                        Stage.Fifth -> HomeFlowerUiModel.partnerFifthState.copy(
                            flowerType = Flower(
                                flowerName = flowerName,
                                userType = UserType.PARTNER,
                                growType = Stage.Fifth,
                            ),
                        )
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
                            Stage.Fifth -> HomeFlowerUiModel.meFifthState.copy(
                                flowerType = Flower(
                                    flowerName = flowerName,
                                    userType = UserType.PARTNER,
                                    growType = Stage.Fifth,
                                ),
                            )
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

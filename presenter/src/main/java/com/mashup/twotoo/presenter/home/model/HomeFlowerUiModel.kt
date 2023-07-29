package com.mashup.twotoo.presenter.home.model

import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.FlowerType
import com.mashup.twotoo.presenter.model.Stage

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeFlowerPartnerAndMeUiModel(
    val authType: AuthType,
    val partner: HomeFlowerUiModel,
    val me: HomeFlowerUiModel,
) : ChallengeStateUiModel {
    companion object {
        val firstChallenge = HomeFlowerPartnerAndMeUiModel(
            authType = AuthType.FirstCreateChallenge,
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.Zero,
                ),
            ),
            me = HomeFlowerUiModel.default,
        )

        val firstChallengeButAuthOnlyPartner = HomeFlowerPartnerAndMeUiModel(
            authType = AuthType.FirstCreateChallengeButAuthOnlyPartner,
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.First,
                ),
            ),
            me = HomeFlowerUiModel.default,
        )

        val authOnlyPartner = HomeFlowerPartnerAndMeUiModel(
            authType = AuthType.AuthOnlyPartner,
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.Zero,
                ),
            ),
            me = HomeFlowerUiModel.default,
        )
        val authOnlyMe = HomeFlowerPartnerAndMeUiModel(
            authType = AuthType.AuthOnlyMe,
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.Fourth,
                ),
            ),
            me = HomeFlowerUiModel.default,
        )
        val authBoth = HomeFlowerPartnerAndMeUiModel(
            authType = AuthType.AuthBoth,
            partner = HomeFlowerUiModel.default,
            me = HomeFlowerUiModel.default,
        )
        val doNotAuthBoth = HomeFlowerPartnerAndMeUiModel(
            authType = AuthType.DoNotAuthBoth,
            partner = HomeFlowerUiModel.default,
            me = HomeFlowerUiModel.default,
        )
    }
}
data class HomeFlowerUiModel(
    val name: String,
    val flowerType: FlowerType,
) {
    companion object {

        val default = HomeFlowerUiModel(
            name = "왕자왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Zero,
            ),
        )

        val me = HomeFlowerUiModel(
            name = "왕",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Fourth,
            ),
        )

        val meZeroState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Zero,
            ),
        )

        val meFirstState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.First,
            ),
        )

        val meSecondState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Second,
            ),
        )

        val meThirdState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Third,
            ),
        )

        val meFourthState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Fourth,
            ),
        )

        val meFifthState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Fifth,
            ),
        )

        val partner = HomeFlowerUiModel(
            name = "공",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Fourth,
            ),
        )

        val partnerZeroState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Zero,
            ),
        )

        val partnerFirstState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.First,
            ),
        )
        val partnerSecondState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Second,
            ),
        )

        val partnerThirdState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Third,
            ),
        )

        val partnerFourthState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Fourth,
            ),
        )

        val partnerFifthState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Fifth,
            ),
        )
    }
}

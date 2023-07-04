package com.mashup.twotoo.presenter.home.model

import com.mashup.twotoo.presenter.home.model.flower.Flower
import com.mashup.twotoo.presenter.home.model.flower.FlowerName
import com.mashup.twotoo.presenter.home.model.flower.FlowerType
import com.mashup.twotoo.presenter.home.model.flower.Stage

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeFlowerPartnerAndMeUiModel(
    val partner: HomeFlowerUiModel,
    val me: HomeFlowerUiModel,
) : ChallengeStateUiModel {
    companion object {
        val firstChallenge = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.Zero,
                ),
            ),
            me = HomeFlowerUiModel.default,
        )
        val authOnlyPartner = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.Zero,
                ),
                authType = AuthType.AuthOnlyPartner,
            ),
            me = HomeFlowerUiModel.default.copy(authType = AuthType.AuthOnlyPartner),
        )
        val authOnlyMe = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Flower(
                    flowerName = FlowerName.Tulip,
                    userType = UserType.PARTNER,
                    growType = Stage.Fourth,
                ),
                authType = AuthType.AuthOnlyMe,
            ),
            me = HomeFlowerUiModel.default.copy(authType = AuthType.AuthOnlyMe),
        )
        val authBoth = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(authType = AuthType.AuthBoth),
            me = HomeFlowerUiModel.default.copy(authType = AuthType.AuthBoth),
        )
        val doNotAuthBoth = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(authType = AuthType.DoNotAuthBoth),
            me = HomeFlowerUiModel.default.copy(authType = AuthType.DoNotAuthBoth),
        )
    }
}
data class HomeFlowerUiModel(
    val name: String,
    val flowerType: FlowerType,
    val authType: AuthType,
) {
    companion object {

        val default = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Zero,
            ),
            authType = AuthType.FirstCreateChallenge,
        )

        val me = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Fourth,
            ),
            authType = AuthType.AuthBoth,
        )

        val meZeroState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Zero,
            ),
            authType = AuthType.AuthBoth,
        )

        val meFirstState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.First,
            ),
            authType = AuthType.AuthBoth,
        )

        val meSecondState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Second,
            ),
            authType = AuthType.AuthBoth,
        )

        val meThirdState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Third,
            ),
            authType = AuthType.AuthBoth,
        )

        val meFourthState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Fourth,
            ),
            authType = AuthType.AuthBoth,
        )

        val meFifthState = HomeFlowerUiModel(
            name = "왕자",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.ME,
                growType = Stage.Fifth,
            ),
            authType = AuthType.AuthBoth,
        )

        val partner = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Fourth,
            ),
            authType = AuthType.AuthBoth,
        )

        val partnerZeroState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Zero,
            ),
            authType = AuthType.DoNotAuthBoth,
        )

        val partnerFirstState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.First,
            ),
            authType = AuthType.DoNotAuthBoth,
        )
        val partnerSecondState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Second,
            ),
            authType = AuthType.DoNotAuthBoth,
        )

        val partnerThirdState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Third,
            ),
            authType = AuthType.DoNotAuthBoth,
        )

        val partnerFourthState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Fourth,
            ),
            authType = AuthType.DoNotAuthBoth,
        )

        val partnerFifthState = HomeFlowerUiModel(
            name = "공주",
            flowerType = Flower(
                flowerName = FlowerName.Tulip,
                userType = UserType.PARTNER,
                growType = Stage.Fifth,
            ),
            authType = AuthType.DoNotAuthBoth,
        )
    }
}

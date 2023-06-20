package com.mashup.twotoo.presenter.home.model

import com.mashup.twotoo.presenter.home.model.flower.FlowerType
import com.mashup.twotoo.presenter.home.model.flower.Stage
import com.mashup.twotoo.presenter.home.model.flower.Tulip

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeFlowerPartnerAndMeUiModel(
    val partner: HomeFlowerUiModel,
    val me: HomeFlowerUiModel,
) {
    companion object {
        val firstChallenge = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Tulip(userType = UserType.PARTNER, growType = Stage.Seed),
            ),
            me = HomeFlowerUiModel.default,
        )
        val authOnlyPartner = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Tulip(userType = UserType.PARTNER, growType = Stage.Seed),
                authType = AuthType.AuthOnlyPartner,
            ),
            me = HomeFlowerUiModel.default.copy(authType = AuthType.AuthOnlyPartner),
        )
        val authOnlyMe = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(
                flowerType = Tulip(userType = UserType.PARTNER, growType = Stage.Seed),
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
            flowerType = Tulip(userType = UserType.ME, growType = Stage.Seed),
            authType = AuthType.FirstCreateChallenge,
        )
    }
}

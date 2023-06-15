package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeFlowerPartnerAndMeUiModel(
    val partner: HomeFlowerUiModel,
    val me: HomeFlowerUiModel,
) {
    companion object {
        val firstChallenge = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default,
            me = HomeFlowerUiModel.default,
        )
        val authOnlyPartner = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(authType = AuthType.AuthOnlyPartner),
            me = HomeFlowerUiModel.default.copy(authType = AuthType.AuthOnlyPartner),
        )
        val authOnlyMe = HomeFlowerPartnerAndMeUiModel(
            partner = HomeFlowerUiModel.default.copy(authType = AuthType.AuthOnlyMe),
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
    val growType: GrowType,
    val authType: AuthType,
) {
    companion object {

        val default = HomeFlowerUiModel(
            name = "왕자",
            growType = GrowType.SEED,
            authType = AuthType.FirstCreateChallenge,
        )
    }
}

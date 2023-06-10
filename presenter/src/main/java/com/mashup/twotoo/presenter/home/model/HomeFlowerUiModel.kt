package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeFlowerUiModel(
    val name: String = "왕자",
    val growType: GrowType = GrowType.SEED,
    val authType: AuthType = AuthType.FirstCreateChallenge,
) {
    companion object {

        val default = HomeFlowerUiModel(
            name = "왕자",
            growType = GrowType.SEED,
            authType = AuthType.FirstCreateChallenge,
        )

        val firstChallengeList = listOf(
            HomeFlowerUiModel(),
            HomeFlowerUiModel(),
        )
        val authOnlyPartnerList = listOf(
            HomeFlowerUiModel().copy(authType = AuthType.AuthOnlyPartner),
            HomeFlowerUiModel().copy(authType = AuthType.AuthOnlyPartner),
        )
        val authOnlyMeList = listOf(
            HomeFlowerUiModel().copy(authType = AuthType.AuthOnlyMe),
            HomeFlowerUiModel().copy(authType = AuthType.AuthOnlyMe),
        )
        val authBoth = listOf(
            HomeFlowerUiModel().copy(authType = AuthType.AuthBoth),
            HomeFlowerUiModel().copy(authType = AuthType.AuthBoth),
        )
        val doNotAuthBoth = listOf(
            HomeFlowerUiModel().copy(authType = AuthType.DoNotAuthBoth),
            HomeFlowerUiModel().copy(authType = AuthType.DoNotAuthBoth),
        )
    }
}

package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeFlower(
    val name: String = "왕자",
    val growType: GrowType = GrowType.SEED,
    val authType: AuthType = AuthType.FirstCreateChallenge,
) {
    companion object {
        val firstChallengeList = listOf(
            HomeFlower(),
            HomeFlower(),
        )
        val authOnlyPartnerList = listOf(
            HomeFlower().copy(authType = AuthType.AuthOnlyPartner),
            HomeFlower().copy(authType = AuthType.AuthOnlyPartner),
        )
        val authOnlyMeList = listOf(
            HomeFlower().copy(authType = AuthType.AuthOnlyMe),
            HomeFlower().copy(authType = AuthType.AuthOnlyMe),
        )
        val authBoth = listOf(
            HomeFlower().copy(authType = AuthType.AuthBoth),
            HomeFlower().copy(authType = AuthType.AuthBoth),
        )
        val doNotAuthBoth = listOf(
            HomeFlower().copy(authType = AuthType.DoNotAuthBoth),
            HomeFlower().copy(authType = AuthType.DoNotAuthBoth),
        )
    }
}

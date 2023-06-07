package com.mashup.twotoo.presenter.home

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R

/**
 * @Created by 김현국 2023/06/07
 */

enum class GrowType(@DrawableRes val growImage: Int, val width: Dp, val height: Dp) {
    SEED(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    SHOOT(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    BUD(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    FLOWER(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
    BLOOM(growImage = R.drawable.ic_seed, width = 25.dp, height = 29.dp),
}

enum class AuthType {
    FirstCreateChallenge,
    AuthOnlyPartner,
    AuthOnlyMe,
    DoNotAuthBoth,
    AuthBoth,
}

enum class UserType {
    ME, PARTNER
}
data class HomeGoalAchieveData(
    val name: String = "공주",
    val type: UserType = UserType.PARTNER,
    val progress: Float = 0.7f,
)

data class HomeGoalFieldData(
    val goal: String = "30분 이상 운동하기",
    val dDay: Int = 24,
)

data class HomeShotCountTextData(
    val count: Int = 4,
)

data class HomeGoalCountData(
    val partnerName: String = "공주",
    val myName: String = "나",
    val count: Int = 1,
)

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

data class HomeModel(
    val homeFlower: List<HomeFlower> = HomeFlower.firstChallengeList,
    val homeGoalAchieveData: HomeGoalAchieveData = HomeGoalAchieveData(),
    val homeGoalCountData: HomeGoalCountData = HomeGoalCountData(),
    val homeGoalFieldData: HomeGoalFieldData = HomeGoalFieldData(),
    val homeShotCountTextData: HomeShotCountTextData = HomeShotCountTextData(),
)

package com.mashup.twotoo.presenter.home

import androidx.annotation.DrawableRes
import com.mashup.twotoo.presenter.R

/**
 * @Created by 김현국 2023/06/07
 */

enum class GrowType(@DrawableRes val growImage: Int) {
    SEED(growImage = R.drawable.ic_seed),
    SHOOT(growImage = R.drawable.ic_seed),
    BUD(growImage = R.drawable.ic_seed),
    FLOWER(growImage = R.drawable.ic_seed),
    BLOOM(growImage = R.drawable.ic_seed),
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
    val name: String,
    val type: UserType,
    val progress: Float,
)

data class HomeGoalFieldData(
    val goal: String = "30분 이상 운동하기",
    val dDay: Int = 24,
)

data class HomeShotCountTextData(
    val count: Int = 4,
)

data class HomeFlower(
    val name: String = "왕자",
    val userType: UserType = UserType.PARTNER,
    val growType: GrowType = GrowType.SEED,
    val authType: AuthType = AuthType.FirstCreateChallenge,
)

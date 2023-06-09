package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeGoalAchieveData(
    val name: String = "공주",
    val type: UserType = UserType.PARTNER,
    val progress: Float = 0.7f,
)

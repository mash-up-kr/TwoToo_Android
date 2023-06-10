package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeGoalAchieveUiModel(
    val name: String,
    val type: UserType,
    val progress: Float,
) {
    companion object {
        val default = HomeGoalAchieveUiModel(
            name = "공주",
            type = UserType.PARTNER,
            progress = 0.7f,
        )
    }
}

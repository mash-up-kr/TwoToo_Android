package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeGoalAchievePartnerAndMeUiModel(
    val partner: HomeGoalAchieveUiModel,
    val me: HomeGoalAchieveUiModel,
) {
    companion object {
        val default = HomeGoalAchievePartnerAndMeUiModel(
            partner = HomeGoalAchieveUiModel.default,
            me = HomeGoalAchieveUiModel.default.copy(
                name = "공주공주",
                type = UserType.ME,
                progress = 0.6f,
            ),
        )
    }
}

data class HomeGoalAchieveUiModel(
    val name: String,
    val type: UserType,
    val progress: Float,
) {
    companion object {
        val default = HomeGoalAchieveUiModel(
            name = "공주공주",
            type = UserType.PARTNER,
            progress = 0.7f,
        )
    }
}

package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeGoalCountUiModel(
    val partnerName: String,
    val myName: String,
    val count: Int,
) {
    companion object {
        val default = HomeGoalCountUiModel(
            partnerName = "공주",
            myName = "나",
            count = 1,
        )
    }
}

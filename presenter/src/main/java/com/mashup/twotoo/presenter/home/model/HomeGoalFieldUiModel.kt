package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeGoalFieldUiModel(
    val goal: String,
    val dDay: Int,
) {
    companion object {
        val default = HomeGoalFieldUiModel(
            goal = "30분 이상 운동하기",
            dDay = 24,
        )
    }
}

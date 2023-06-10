package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/07
 */

data class HomeUiModel(
    val homeFlowerUiModels: List<HomeFlowerUiModel> = listOf(HomeFlowerUiModel.default, HomeFlowerUiModel.default),
    val homeGoalAchieveUiModel: HomeGoalAchieveUiModel = HomeGoalAchieveUiModel.default,
    val homeGoalCountUiModel: HomeGoalCountUiModel = HomeGoalCountUiModel.default,
    val homeGoalFieldUiModel: HomeGoalFieldUiModel = HomeGoalFieldUiModel.default,
    val homeShotCountTextUiModel: HomeShotCountTextUiModel = HomeShotCountTextUiModel.default,
)

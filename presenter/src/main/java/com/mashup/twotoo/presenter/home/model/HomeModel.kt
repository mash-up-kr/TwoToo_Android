package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/07
 */

data class HomeModel(
    val homeFlower: List<HomeFlower> = HomeFlower.firstChallengeList,
    val homeGoalAchieveData: HomeGoalAchieveData = HomeGoalAchieveData(),
    val homeGoalCountData: HomeGoalCountData = HomeGoalCountData(),
    val homeGoalFieldData: HomeGoalFieldData = HomeGoalFieldData(),
    val homeShotCountTextData: HomeShotCountTextData = HomeShotCountTextData(),
)

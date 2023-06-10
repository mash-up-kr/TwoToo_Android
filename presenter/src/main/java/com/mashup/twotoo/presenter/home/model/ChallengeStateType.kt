package com.mashup.twotoo.presenter.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState.EMPTY

/**
 * @Created by 김현국 2023/06/10
 */

sealed interface ChallengeStateTypeUiModel

enum class BeforeChallengeState {
    EMPTY, REQUEST, RECEIVE, WAIT, TERMINATION
}

data class BeforeChallengeUiModel(
    val state: BeforeChallengeState,
    val homeGoalCountUiModel: HomeGoalCountUiModel,
    val stateTitleUiModel: StateTitleUiModel,
    @DrawableRes val stateImage: Int,
    @StringRes val buttonText: Int,
) : ChallengeStateTypeUiModel {
    companion object {
        val default = BeforeChallengeUiModel(
            state = EMPTY,
            homeGoalCountUiModel = HomeGoalCountUiModel.default,
            stateTitleUiModel = StateTitleUiModel.default,
            stateImage = R.drawable.image_challenge_empty,
            buttonText = R.string.homeBeforeChallengeEmptyButtonText,
        )
    }
}

data class AfterChallengeUiModel(
    val homeFlowerUiModels: List<HomeFlowerUiModel>,
    val homeGoalAchieveUiModel: HomeGoalAchieveUiModel,
    val homeGoalCountUiModel: HomeGoalCountUiModel,
    val homeGoalFieldUiModel: HomeGoalFieldUiModel,
    val homeShotCountTextUiModel: HomeShotCountTextUiModel,
) : ChallengeStateTypeUiModel {
    companion object {
        val default = AfterChallengeUiModel(
            homeFlowerUiModels = HomeFlowerUiModel.firstChallengeList,
            homeGoalAchieveUiModel = HomeGoalAchieveUiModel.default,
            homeGoalCountUiModel = HomeGoalCountUiModel.default,
            homeGoalFieldUiModel = HomeGoalFieldUiModel.default,
            homeShotCountTextUiModel = HomeShotCountTextUiModel.default,
        )
    }
}

data class StateTitleUiModel(
    @StringRes val title: Int,
    @StringRes val subTitle: Int? = null,
) {
    companion object {
        val default = StateTitleUiModel(
            title = R.string.homeBeforeChallengeEmpty,
            subTitle = null,
        )
    }
}

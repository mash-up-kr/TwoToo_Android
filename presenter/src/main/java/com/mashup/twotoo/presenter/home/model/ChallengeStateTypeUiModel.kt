package com.mashup.twotoo.presenter.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState.EMPTY
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState.REQUEST
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState.RESPONSE
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState.TERMINATION
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState.WAIT

/**
 * @Created by 김현국 2023/06/10
 */

sealed interface ChallengeStateTypeUiModel

enum class BeforeChallengeState {
    EMPTY, REQUEST, RESPONSE, WAIT, TERMINATION
}

data class BeforeChallengeUiModel(
    val state: BeforeChallengeState,
    val homeGoalCountUiModel: HomeGoalCountUiModel,
    val stateTitleUiModel: StateTitleUiModel,
    @DrawableRes val stateImage: Int,
    @StringRes val buttonText: Int,
) : ChallengeStateTypeUiModel {
    companion object {
        val empty = BeforeChallengeUiModel(
            state = EMPTY,
            homeGoalCountUiModel = HomeGoalCountUiModel.empty,
            stateTitleUiModel = StateTitleUiModel.empty,
            stateImage = R.drawable.image_challenge_empty,
            buttonText = R.string.homeBeforeChallengeEmptyButtonText,
        )
        val request = BeforeChallengeUiModel(
            state = REQUEST,
            homeGoalCountUiModel = HomeGoalCountUiModel.empty,
            stateTitleUiModel = StateTitleUiModel.request,
            stateImage = R.drawable.image_challenge_empty,
            buttonText = R.string.homeBeforeChallengeRequestButtonText,
        )
        val response = BeforeChallengeUiModel(
            state = RESPONSE,
            homeGoalCountUiModel = HomeGoalCountUiModel.empty,
            stateTitleUiModel = StateTitleUiModel.response,
            stateImage = R.drawable.image_challenge_response,
            buttonText = R.string.homeBeforeChallengeRequestButtonText,
        )

        val wait = BeforeChallengeUiModel(
            state = WAIT,
            homeGoalCountUiModel = HomeGoalCountUiModel.empty,
            stateTitleUiModel = StateTitleUiModel.wait,
            stateImage = R.drawable.image_challenge_empty,
            buttonText = R.string.homeBeforeChallengeWaitButtonText,
        )

        val termination = BeforeChallengeUiModel(
            state = TERMINATION,
            homeGoalCountUiModel = HomeGoalCountUiModel.empty,
            stateTitleUiModel = StateTitleUiModel.termination,
            stateImage = R.drawable.image_challenge_empty,
            buttonText = R.string.homeBeforeChallengeTerminationButtonText,
        )
    }
}

data class OngoingChallengeUiModel(
    val homeFlowerUiModels: HomeFlowerPartnerAndMeUiModel,
    val homeGoalAchieveUiModel: HomeGoalAchieveUiModel,
    val homeGoalCountUiModel: HomeGoalCountUiModel,
    val homeGoalFieldUiModel: HomeGoalFieldUiModel,
    val homeShotCountTextUiModel: HomeShotCountTextUiModel,
) : ChallengeStateTypeUiModel {
    companion object {
        val default = OngoingChallengeUiModel(
            homeFlowerUiModels = HomeFlowerPartnerAndMeUiModel.firstChallenge,
            homeGoalAchieveUiModel = HomeGoalAchieveUiModel.default,
            homeGoalCountUiModel = HomeGoalCountUiModel.empty,
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
        val empty = StateTitleUiModel(
            title = R.string.homeBeforeChallengeEmpty,
            subTitle = null,
        )
        val request = StateTitleUiModel(
            title = R.string.homeBeforeChallengeRequest,
        )
        val response = StateTitleUiModel(
            title = R.string.homeBeforeChallengeResponse,
        )
        val wait = StateTitleUiModel(
            title = R.string.homeBeforeChallengeWait,
            subTitle = R.string.homeBeforeChallengeWaitSubTitle,
        )
        val termination = StateTitleUiModel(
            title = R.string.homeBeforeChallengeTermination,
        )
    }
}
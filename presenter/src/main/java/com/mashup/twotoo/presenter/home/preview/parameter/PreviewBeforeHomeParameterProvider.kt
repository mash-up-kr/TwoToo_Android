package com.mashup.twotoo.presenter.home.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel

class PreviewBeforeHomeParameterProvider : PreviewParameterProvider<HomeStateUiModel> {
    override val values = listOf(
        beforeEmptyChallenge,
        beforeRequestChallenge,
        beforeResponseChallenge,
        beforeWaitChallenge,
        beforeTerminationChallenge,
    ).asSequence()

    companion object {
        val beforeEmptyChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.empty,
        )
        val beforeRequestChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.request,
        )
        val beforeResponseChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.response,
        )
        val beforeWaitChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.wait,
        )
        val beforeTerminationChallenge = HomeStateUiModel(
            challengeStateUiModel = BeforeChallengeUiModel.termination,
        )
    }
}

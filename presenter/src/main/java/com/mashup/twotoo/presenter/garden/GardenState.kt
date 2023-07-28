package com.mashup.twotoo.presenter.garden

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel

data class GardenState(
    val challengeCardInfos: List<ChallengeCardInfoUiModel> = emptyList(),
    val startAnimation: Pair<Boolean, Int> = Pair(false, 0),
) {
    companion object {
        val default = GardenState(
            challengeCardInfos = ChallengeCardInfoUiModel.default,
        )
    }
}

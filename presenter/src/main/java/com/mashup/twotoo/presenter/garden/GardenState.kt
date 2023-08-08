package com.mashup.twotoo.presenter.garden

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class GardenState(
    val challengeCardInfos: ImmutableList<ChallengeCardInfoUiModel> = persistentListOf(),
    val loadingIndicatorState: Boolean = false,
    val hasNotRealChallenge: Boolean = false,
) {
    companion object {
        val default = GardenState(
            challengeCardInfos = ChallengeCardInfoUiModel.default.toImmutableList(),
        )
    }
}

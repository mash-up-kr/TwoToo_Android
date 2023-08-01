package com.mashup.twotoo.presenter.history

import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel

data class HistoryState(
    val loadingIndicatorState: Boolean = false,
    val challengeInfoUiModel: ChallengeInfoUiModel = ChallengeInfoUiModel(),
    val historyItemUiModel: List<HistoryItemUiModel> = emptyList(),
    val ownerNickNamesUiModel: OwnerNickNamesUiModel = OwnerNickNamesUiModel(),
    val historyDetailInfoUiModel: HistoryDetailInfoUiModel = HistoryDetailInfoUiModel(),
) {
    companion object {
        val default = HistoryState(
            challengeInfoUiModel = ChallengeInfoUiModel.default,
            historyItemUiModel = HistoryItemUiModel.default,
            ownerNickNamesUiModel = OwnerNickNamesUiModel.default,
            historyDetailInfoUiModel = HistoryDetailInfoUiModel.default,
        )
    }
}

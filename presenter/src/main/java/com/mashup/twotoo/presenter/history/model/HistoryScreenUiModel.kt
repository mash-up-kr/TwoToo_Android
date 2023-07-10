package com.mashup.twotoo.presenter.history.model

data class HistoryScreenUiModel(
    val challengeInfoUiModel: ChallengeInfoUiModel,
    val historyItemUiModel: List<HistoryItemUiModel>,
    val ownerNickNamesUiModel: OwnerNickNamesUiModel,
) {
    companion object {
        val default = HistoryScreenUiModel(
            challengeInfoUiModel = ChallengeInfoUiModel.default,
            historyItemUiModel = HistoryItemUiModel.default,
            ownerNickNamesUiModel = OwnerNickNamesUiModel.default,
        )
    }
}

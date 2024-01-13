package com.mashup.twotoo.presenter.history.datail.model

import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import com.mashup.twotoo.presenter.history.model.toHistoryDetailInfoUiModel
import model.challenge.response.HomeViewResponseDomainModel

data class HistoryDetailInfoUiModel(
    val ownerNickNamesUiModel: OwnerNickNamesUiModel = OwnerNickNamesUiModel.default,
    val challengeName: String = "",
    val infoUiModel: HistoryInfoUiModel = HistoryInfoUiModel.empty,
) {
    companion object {
        val default: HistoryDetailInfoUiModel = HistoryDetailInfoUiModel(
            ownerNickNamesUiModel = OwnerNickNamesUiModel.default,
            challengeName = "30분 이상 운동하기",
            infoUiModel = HistoryInfoUiModel.default,
        )
    }
}

fun HomeViewResponseDomainModel.toHistoryDetailInfoUiModel(): HistoryDetailInfoUiModel {
    return HistoryDetailInfoUiModel(
        ownerNickNamesUiModel = OwnerNickNamesUiModel.from(partnerInfo, myInfo),
        challengeName = onGoingChallenge?.name ?: "",
        infoUiModel = partnerCommit?.toHistoryDetailInfoUiModel() ?: HistoryInfoUiModel.empty,
    )
}

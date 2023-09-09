package com.mashup.twotoo.presenter.garden.mapper

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import com.mashup.twotoo.presenter.model.toFlowerName
import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.response.HistoryChallengeDomainModel

fun HistoryChallengeDomainModel.toUiModel(index: Int): ChallengeCardInfoUiModel {
    return ChallengeCardInfoUiModel(
        viewState = this.viewState ?: "InProgress",
        challengeNo = challengeNo,
        attempts = index + 1,
        name = name,
        period = toPeriod(),
        meFlower = user1Flower.toFlowerName(),
        partnerFlower = user2Flower.toFlowerName(),
        user1CommitCnt = user1CommitCnt,
        user2CommitCnt = user2CommitCnt,
    )
}

fun HistoryChallengeDomainModel.toPeriod(): String {
    return "${DateFormatter.dateConvertToPlusNineTime(startDate,"yyyy-MM-dd")} ~ ${DateFormatter.dateConvertToPlusNineTime(endDate,"yyyy-MM-dd")}"
}

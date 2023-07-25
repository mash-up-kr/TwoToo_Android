package com.mashup.twotoo.presenter.garden.mapper

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import com.mashup.twotoo.presenter.model.toFlowerName
import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.response.ChallengeResponseDomainModel

fun ChallengeResponseDomainModel.toUiModel(index: Int): ChallengeCardInfoUiModel {
    return ChallengeCardInfoUiModel(
        challengeNo = challengeNo,
        attempts = index + 1,
        name = name,
        period = toPeriod(),
        meFlower = user1Flower.toFlowerName(),
        partnerFlower = user2Flower.toFlowerName(),
    )
}

fun ChallengeResponseDomainModel.toPeriod(): String {
    return "${DateFormatter.getDateStrByStr(startDate)} ~ ${DateFormatter.getDateStrByStr(endDate)}"
}

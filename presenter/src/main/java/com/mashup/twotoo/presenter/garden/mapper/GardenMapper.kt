package com.mashup.twotoo.presenter.garden.mapper

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import com.mashup.twotoo.presenter.home.mapper.toFlowerName
import com.mashup.twotoo.presenter.model.FlowerName
import model.challenge.response.ChallengeResponseDomainModel

fun ChallengeResponseDomainModel.toUiModel(index: Int): ChallengeCardInfoUiModel {
    return ChallengeCardInfoUiModel(
        attempts = index.toString(),
        name = name,
        period = toPeriod(),
        meFlower = user1Flower.toFlowerName(),
        partnerFlower = user2Flower.toFlowerName(),
    )
}

fun String.toDate(): String {
    val index = indexOf('T')
    return if (index != -1) {
        substring(0, index)
    } else {
        this
    }
}

fun ChallengeResponseDomainModel.toPeriod(): String {
    return "${startDate.toDate()} ~ ${endDate.toDate()}"
}

fun String.toFlowerName(): FlowerName =
    FlowerName.findBy(this)

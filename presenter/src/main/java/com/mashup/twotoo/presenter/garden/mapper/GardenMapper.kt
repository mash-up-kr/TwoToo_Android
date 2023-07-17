package com.mashup.twotoo.presenter.garden.mapper

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import model.challenge.response.ChallengeResponseDomainModel

fun ChallengeResponseDomainModel.toUiModel(index: Int): ChallengeCardInfoUiModel {
    return ChallengeCardInfoUiModel(
        attempts = index.toString(),
        name = name,
        period = toPeriod(),
    )
}

fun ChallengeResponseDomainModel.toUiDate(date: String): String {
    val index = date.indexOf('T')
    return if (index != -1) {
        date.substring(0, index)
    } else {
        date
    }
}

fun ChallengeResponseDomainModel.toPeriod(): String {
    return "${toUiDate(startDate)} ~ ${toUiDate(endDate)}"
}

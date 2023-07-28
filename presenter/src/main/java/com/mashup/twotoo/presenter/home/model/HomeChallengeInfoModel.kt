package com.mashup.twotoo.presenter.home.model

import model.challenge.response.ChallengeResponseDomainModel

data class HomeChallengeInfoModel(
    val challengeNo: Int = 0,
    val name: String = "",
    val description: String = "",
    val startDate: String = "",
    val endDate: String = "",
)

fun ChallengeResponseDomainModel?.toUiModel(): HomeChallengeInfoModel {
    this?.let { model ->
        return HomeChallengeInfoModel(
            challengeNo = model.challengeNo,
            name = model.name,
            description = model.description,
            startDate = model.startDate,
            endDate = model.endDate,
        )
    }
    return HomeChallengeInfoModel()
}

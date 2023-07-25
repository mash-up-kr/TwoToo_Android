package com.mashup.twotoo.presenter.createChallenge.model

import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.request.CreateChallengeRequestDomainModel

data class ChallengeInfoModel(
    var challengeName: String = "",
    var startDate: String = "",
    val endDate: String = "",
    val period: String = "",
    var challengeInfo: String = "",
    val selectFlowerName: String = ""
)

fun ChallengeInfoModel.toDomainModel(): CreateChallengeRequestDomainModel {
    return CreateChallengeRequestDomainModel(
        name = this.challengeName,
        description = this.challengeInfo,
        startDate = DateFormatter.convertToIsoTime(this.startDate) ?: this.startDate,
        user2Flower = this.selectFlowerName,
    )
}

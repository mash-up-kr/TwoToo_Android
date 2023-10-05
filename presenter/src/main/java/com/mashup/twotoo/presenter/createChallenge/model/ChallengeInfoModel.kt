package com.mashup.twotoo.presenter.createChallenge.model

import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.request.CreateChallengeRequestDomainModel

data class ChallengeInfoModel(
    val challengeNo: Int = 0,
    val homeState: String = BeforeChallengeState.EMPTY.name,
    val currentStep: Int = 1,
    val isBack: Boolean = false,
    val challengeName: String = "",
    val startDate: String = DateFormatter.getCurrentDate(),
    val endDate: String = DateFormatter.getDaysAfter(DateFormatter.getCurrentDate()),
    val period: String = "",
    val challengeInfo: String = "",
    val selectFlowerName: String = "",
    val selectDialogVisibility: Boolean = false,
    val dialogVisibility: Boolean = false,
)

fun ChallengeInfoModel.toDomainModel(): CreateChallengeRequestDomainModel {
    return CreateChallengeRequestDomainModel(
        name = this.challengeName,
        description = this.challengeInfo,
        startDate = DateFormatter.convertToIsoTime(this.startDate) ?: this.startDate,
        user2Flower = this.selectFlowerName,
    )
}

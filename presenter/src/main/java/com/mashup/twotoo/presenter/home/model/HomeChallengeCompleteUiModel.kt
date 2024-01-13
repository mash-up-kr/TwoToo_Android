package com.mashup.twotoo.presenter.home.model

import android.content.Context
import com.mashup.twotoo.presenter.home.mapper.getFlowerType
import com.mashup.twotoo.presenter.model.Stage
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HomeViewResponseDomainModel

data class HomeChallengeCompleteUiModel(
    val count: Int = 0,
    val name: String = "",
    val description: String = "",
    val startDate: String = "",
    val myFlower: String = "",
    val partnerFlower: String = "",
) {
    fun getFlowerImage(context: Context, userType: UserType): Int {
        val (flower, userType) = if (userType == UserType.ME) (myFlower to "me") else (partnerFlower to "partner")
        val name = "img_home_${Stage.Fifth.name.lowercase()}_stage_${flower.lowercase()}_$userType"
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }
}

fun HomeViewResponseDomainModel?.toCardUiModel(): HomeChallengeCompleteUiModel {
    this?.let {
        val challengeInfo = this.onGoingChallenge ?: ChallengeResponseDomainModel.default
        val myInfo = this.myInfo.userNo
        return HomeChallengeCompleteUiModel(
            count = this.challengeTotal,
            name = challengeInfo.name,
            description = challengeInfo.description,
            startDate = challengeInfo.startDate,
            myFlower = challengeInfo.getFlowerType(myInfo).first.name.value,
            partnerFlower = challengeInfo.getFlowerType(myInfo).second.name.value,
        )
    }
    return HomeChallengeCompleteUiModel()
}


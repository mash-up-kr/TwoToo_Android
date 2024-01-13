package com.mashup.twotoo.presenter.home.model

import android.content.Context
import com.mashup.twotoo.presenter.model.Stage
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HomeViewResponseDomainModel

data class HomeChallengeCompleteUiModel(
    val count: Int = 0,
    val name: String = "",
    val description: String = "",
    val startDate: String = "",
    val user1Flower: String = "",
    val user2Flower: String = "",
) {
    fun getFlowerImage(context: Context, userNum: Int): Int {
        val flower = if (userNum == 1) user1Flower else user2Flower
        val name = "img_home_${Stage.Fifth.name.lowercase()}_stage_${flower.lowercase()}_me"
        val image = context.resources.getIdentifier(name, "drawable", context.packageName)
        return image
    }
}

fun HomeViewResponseDomainModel?.toCardUiModel(): HomeChallengeCompleteUiModel {
    this?.let {
        val challengeInfo = this.onGoingChallenge ?: ChallengeResponseDomainModel.default
        return HomeChallengeCompleteUiModel(
            count = this.challengeTotal,
            name = challengeInfo.name,
            description = challengeInfo.description,
            startDate = challengeInfo.startDate,
            user1Flower = challengeInfo.user1Flower,
            user2Flower = challengeInfo.user2Flower,
        )
    }
    return HomeChallengeCompleteUiModel()
}

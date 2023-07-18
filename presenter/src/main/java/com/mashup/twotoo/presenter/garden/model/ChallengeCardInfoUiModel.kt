package com.mashup.twotoo.presenter.garden.model

import com.mashup.twotoo.presenter.model.FlowerName

data class ChallengeCardInfoUiModel(
    val attempts: String,
    val name: String,
    val period: String,
    val meFlower: FlowerName,
    val partnerFlower: FlowerName,
) {
    companion object {
        val default: List<ChallengeCardInfoUiModel> = listOf(
            ChallengeCardInfoUiModel("1", "하루 30분 운동하기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel("2", "아침 밥 먹기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel("3", "하루 2시간 공부하기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel("4", "하루 책 20page 읽기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
        )
    }
}

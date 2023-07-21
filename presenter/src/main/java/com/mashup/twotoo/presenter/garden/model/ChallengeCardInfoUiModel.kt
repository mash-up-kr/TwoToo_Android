package com.mashup.twotoo.presenter.garden.model

import com.mashup.twotoo.presenter.model.FlowerName

data class ChallengeCardInfoUiModel(
    val challengeNo: Int,
    val attempts: String,
    val name: String,
    val period: String,
    val meFlower: FlowerName,
    val partnerFlower: FlowerName,
) {
    companion object {
        // Todo 14지우고 garden Card challengeNo와 매핑해야함.
        val default: List<ChallengeCardInfoUiModel> = listOf(
            ChallengeCardInfoUiModel(14, "1번째 챌린지", "하루 30분 운동하기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel(14, "2번째 챌린지", "아침 밥 먹기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel(14, "3번째 챌린지", "하루 2시간 공부하기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel(14, "4번째 챌린지", "하루 책 20page 읽기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
        )
    }
}

package com.mashup.twotoo.presenter.garden.model

import com.mashup.twotoo.presenter.model.FlowerName

data class ChallengeCardInfoUiModel(
    val challengeNo: Int,
    val attempts: Int,
    val name: String,
    val period: String,
    val meFlower: FlowerName,
    val partnerFlower: FlowerName,
    val user1CommitCnt: Int = 0,
    val user2CommitCnt: Int = 0
) {
    companion object {
        // Todo 14지우고 garden Card challengeNo와 매핑해야함.
        val default: List<ChallengeCardInfoUiModel> = listOf(
            ChallengeCardInfoUiModel(14, 1, "하루 30분 운동하기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel(14, 2, "아침 밥 먹기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel(14, 3, "하루 2시간 공부하기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
            ChallengeCardInfoUiModel(14, 4, "하루 책 20page 읽기", "2023/05/01 ~ 05/22", FlowerName.Fig, FlowerName.Camellia),
        )
    }
}

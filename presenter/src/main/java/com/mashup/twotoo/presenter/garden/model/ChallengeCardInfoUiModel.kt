package com.mashup.twotoo.presenter.garden.model

data class ChallengeCardInfoUiModel(
    val attempts: String,
    val name: String,
    val period: String,
) {
    companion object {
        val default: List<ChallengeCardInfoUiModel> = listOf(
            ChallengeCardInfoUiModel("1번째 챌린지", "하루 30분 운동하기", "2023/05/01 ~ 05/22"),
            ChallengeCardInfoUiModel("2번째 챌린지", "아침 밥 먹기", "2023/05/01 ~ 05/22"),
            ChallengeCardInfoUiModel("3번째 챌린지", "하루 2시간 공부하기", "2023/05/01 ~ 05/22"),
            ChallengeCardInfoUiModel("4번째 챌린지", "하루 책 20page 읽기", "2023/05/01 ~ 05/22"),
        )
    }
}

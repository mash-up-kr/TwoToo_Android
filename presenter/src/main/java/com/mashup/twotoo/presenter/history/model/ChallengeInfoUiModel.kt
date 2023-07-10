package com.mashup.twotoo.presenter.history.model

data class ChallengeInfoUiModel(
    val day: String,
    val name: String,
    val detail: String,
) {
    companion object {
        val default = ChallengeInfoUiModel(
            "24",
            "30분 이상 운동하기",
            "운동 사진으로 인증하기\n인증 실패하는지 확인",
        )
    }
}

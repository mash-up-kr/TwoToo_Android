package com.mashup.twotoo.presenter.history.model

import model.challenge.response.ChallengeResponseDomainModel

data class ChallengeInfoUiModel(
    val day: String = "",
    val name: String = "",
    val detail: String = "",
) {
    companion object {
        val default = ChallengeInfoUiModel(
            "24",
            "30분 이상 운동하기",
            "운동 사진으로 인증하기\n인증 실패하는지 확인",
        )

        // Todo Day 계산할 수 있도록 수정해야함
        fun from(challenge: ChallengeResponseDomainModel): ChallengeInfoUiModel {
            return ChallengeInfoUiModel(
                day = "24",
                name = challenge.name,
                detail = challenge.description,
            )
        }
    }
}

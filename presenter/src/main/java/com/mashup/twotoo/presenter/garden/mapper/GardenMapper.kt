package com.mashup.twotoo.presenter.garden.mapper

import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import model.challenge.response.ChallengeResponseDomainModel

fun ChallengeResponseDomainModel.toUiModel(): ChallengeCardInfoUiModel {
    return ChallengeCardInfoUiModel(
        attempts = "${challengeNo}번째 챌린지",
        name = name,
        period = "$startDate ~ $endDate",
    )
}

package com.mashup.twotoo.presenter.history.model

import model.challenge.response.ChallengeResponseDomainModel

fun createFakeChallengeResponseDomainModel(
    startDate: String,
    endDate: String,
    user1CommitCnt: Int,
    user2CommitCnt: Int,
): ChallengeResponseDomainModel = ChallengeResponseDomainModel.default.copy(
    startDate = startDate,
    endDate = endDate,
    user1CommitCnt = user1CommitCnt,
    user2CommitCnt = user2CommitCnt,
)

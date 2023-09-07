package com.mashup.twotoo.presenter.repository

import com.mashup.twotoo.presenter.history.model.createFakeChallengeResponseDomainModel
import com.mashup.twotoo.presenter.history.model.generateFakeCommitList
import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeDetailResponseDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository

class FakeChallengeRepository : ChallengeRepository {
    override suspend fun createChallenge(createChallengeRequestDomainModel: CreateChallengeRequestDomainModel): Result<ChallengeResponseDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllChallenge(): Result<List<ChallengeResponseDomainModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getChallengeByNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Result<ChallengeDetailResponseDomainModel> {
        return Result.success(
            ChallengeDetailResponseDomainModel(
                challengeResponseDomainModel = createFakeChallengeResponseDomainModel(
                    "2023-08-28T06:03:47.615Z",
                    "2023-09-15T06:03:47.615Z",
                    0,
                    0,
                ),
                myCommitResponseDomainModel = generateFakeCommitList(
                    startDate = "2023-08-28T06:03:47.615Z",
                    count = 5,
                    userNo = 1,
                ),
                partnerCommitResponseDomainModel = generateFakeCommitList(
                    startDate = "2023-08-29T06:03:47.615Z",
                    count = 6,
                    userNo = 2,
                ),
            ),
        )
    }

    override suspend fun quitChallenge(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): Result<ChallengeResponseDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun finishChallengeWithNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Result<ChallengeResponseDomainModel> {
        TODO("Not yet implemented")
    }
}

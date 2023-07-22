package com.mashup.twotoo.presenter.home.repository

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
        TODO("Not yet implemented")
    }

    override suspend fun quitChallenge(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): ChallengeResponseDomainModel {
        TODO("Not yet implemented")
    }

    override suspend fun finishChallengeWithNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Result<ChallengeResponseDomainModel> {
        TODO("Not yet implemented")
    }
}

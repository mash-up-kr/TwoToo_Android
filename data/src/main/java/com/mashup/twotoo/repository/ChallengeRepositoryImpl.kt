package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.challenge.ChallengeDataSource
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource,
) : ChallengeRepository {
    override suspend fun createChallenge(createChallengeRequestDomainModel: CreateChallengeRequestDomainModel): Result<ChallengeResponseDomainModel> {
        return runCatching {
            challengeDataSource.createChallenge(createChallengeRequestDomainModel.toDataModel()).toDomainModel()
        }
    }

    override suspend fun getAllChallenge(): Result<List<ChallengeResponseDomainModel>> {
        return runCatching {
            challengeDataSource.getAllChallenge().map { challenge: Challenge ->
                challenge.toDomainModel()
            }
        }
    }

    override suspend fun getChallengeByNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): ChallengeResponseDomainModel {
        return challengeDataSource.getChallengeByNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
        ).toDomainModel()
    }

    override suspend fun quitChallenge(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Int {
        return challengeDataSource.deleteChallengeByNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
        )
    }

    override suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): ChallengeResponseDomainModel {
        return challengeDataSource.approveChallengeWithNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
            approveChallengeRequest = approveChallengeRequestDomainModel.toDataModel(),
        ).toDomainModel()
    }

    override suspend fun finishChallengeWithNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): Result<ChallengeResponseDomainModel> {
        return runCatching {
            challengeDataSource.finishChallengeWithNo(
                challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
            ).toDomainModel()
        }
    }
}

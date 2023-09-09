package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.challenge.ChallengeDataSource
import com.mashup.twotoo.datasource.remote.challenge.request.toDataModel
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge.Companion.toHistoryChallengeDomainModel
import com.mashup.twotoo.datasource.remote.challenge.response.toDomainModel
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeDetailResponseDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HistoryChallengeDomainModel
import repository.ChallengeRepository
import util.NetworkResult
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource,
) : ChallengeRepository {
    override suspend fun createChallenge(createChallengeRequestDomainModel: CreateChallengeRequestDomainModel): NetworkResult<ChallengeResponseDomainModel> {
        return challengeDataSource.createChallenge(createChallengeRequestDomainModel.toDataModel()).map { challenge ->
            challenge.toDomainModel()
        }
    }

    override suspend fun getAllChallenge(): NetworkResult<List<ChallengeResponseDomainModel>> {
        return challengeDataSource.getAllChallenge().map { list ->
            list.map { challenge ->
                challenge.toDomainModel()
            }
        }
    }

    override suspend fun getChallengeByNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): NetworkResult<ChallengeDetailResponseDomainModel> {
        return challengeDataSource.getChallengeByNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
        ).map { challengeDetail ->
            challengeDetail.toDomainModel()
        }
    }

    override suspend fun quitChallenge(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): NetworkResult<Int> {
        return challengeDataSource.deleteChallengeByNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
        )
    }

    override suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): NetworkResult<ChallengeResponseDomainModel> {
        return challengeDataSource.approveChallengeWithNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
            approveChallengeRequest = approveChallengeRequestDomainModel.toDataModel(),
        ).map { challenge ->
            challenge.toDomainModel()
        }
    }

    override suspend fun finishChallengeWithNo(challengeNoRequestDomainModel: ChallengeNoRequestDomainModel): NetworkResult<ChallengeResponseDomainModel> {
        return challengeDataSource.finishChallengeWithNo(
            challengeNoRequest = challengeNoRequestDomainModel.toDataModel(),
        ).map { challenge ->
            challenge.toDomainModel()
        }
    }

    override suspend fun getChallengeHistories(): NetworkResult<List<HistoryChallengeDomainModel>> {
        return challengeDataSource.getChallengeHistories().map { list ->
            list.map { challenge ->
                challenge.toHistoryChallengeDomainModel()
            }
        }
    }
}

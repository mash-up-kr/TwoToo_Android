package repository

import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeDetailResponseDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HistoryChallengeDomainModel
import util.NetworkResult

/**
 * @Created by 김현국 2023/07/04
 */

interface ChallengeRepository {
    suspend fun createChallenge(
        createChallengeRequestDomainModel: CreateChallengeRequestDomainModel,
    ): NetworkResult<ChallengeResponseDomainModel>

    suspend fun getAllChallenge(): NetworkResult<List<ChallengeResponseDomainModel>>

    suspend fun getChallengeByNo(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): NetworkResult<ChallengeDetailResponseDomainModel>

    suspend fun quitChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): NetworkResult<Int>

    suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): NetworkResult<ChallengeResponseDomainModel>

    suspend fun finishChallengeWithNo(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): NetworkResult<ChallengeResponseDomainModel>

    suspend fun getChallengeHistories(): NetworkResult<List<HistoryChallengeDomainModel>>
}

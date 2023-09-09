package repository

import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeDetailResponseDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HistoryChallengeDomainModel

/**
 * @Created by 김현국 2023/07/04
 */

interface ChallengeRepository {
    suspend fun createChallenge(
        createChallengeRequestDomainModel: CreateChallengeRequestDomainModel,
    ): Result<ChallengeResponseDomainModel>

    suspend fun getAllChallenge(): Result<List<ChallengeResponseDomainModel>>

    suspend fun getChallengeByNo(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): Result<ChallengeDetailResponseDomainModel>

    suspend fun quitChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): Result<Int>

    suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): Result<ChallengeResponseDomainModel>

    suspend fun finishChallengeWithNo(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): Result<ChallengeResponseDomainModel>

    suspend fun getChallengeHistories(): Result<List<HistoryChallengeDomainModel>>
}

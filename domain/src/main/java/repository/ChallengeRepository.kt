package repository

import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel

/**
 * @Created by 김현국 2023/07/04
 */

interface ChallengeRepository {
    suspend fun createChallenge(
        createChallengeRequestDomainModel: CreateChallengeRequestDomainModel,
    ): Result<ChallengeResponseDomainModel>

    suspend fun getAllChallenge(): List<ChallengeResponseDomainModel>

    suspend fun getChallengeByNo(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): ChallengeResponseDomainModel

    suspend fun quitChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): Int

    suspend fun approveChallenge(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel,
    ): ChallengeResponseDomainModel
}

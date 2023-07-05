package repository

import model.HomeDomainModel

/**
 * @Created by 김현국 2023/07/04
 */
interface ChallengeRepository {
    suspend fun createChallenge(): Result<HomeDomainModel>
    suspend fun approveChallenge()
    suspend fun getHomeState()
    suspend fun pushSting()
}

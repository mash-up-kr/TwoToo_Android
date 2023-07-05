package repository

import model.challenge.HomeViewDomainModel

/**
 * @Created by 김현국 2023/07/04
 */
interface ChallengeRepository {
    suspend fun createChallenge(): Result<HomeViewDomainModel>
    suspend fun approveChallenge()
    suspend fun getHomeViewState(): Result<HomeViewDomainModel>
    suspend fun pushSting()
}

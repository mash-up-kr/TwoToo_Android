package repository

/**
 * @Created by 김현국 2023/07/04
 */
interface ChallengeRepository {
    suspend fun createChallenge()
    suspend fun approveChallenge()
    suspend fun pushSting()
}

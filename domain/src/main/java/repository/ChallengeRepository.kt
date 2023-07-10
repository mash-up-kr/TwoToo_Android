package repository


interface ChallengeRepository {
    suspend fun createChallenge()
    suspend fun approveChallenge()
    suspend fun pushSting()
}

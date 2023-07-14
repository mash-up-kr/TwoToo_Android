package model.challenge.response

/**
 * @Created by 김현국 2023/07/05
 */
data class ChallengeResponseDomainModel(
    val challengeNo: Int,
    val endDate: String,
    val isApproved: Boolean,
    val isFinished: Boolean,
    val name: String,
    val startDate: String,
    val user1: UserResponseDomainModel,
    val user1CommitCnt: Int,
    val user1Flower: String,
    val user2: UserResponseDomainModel,
    val user2CommitCnt: Int,
    val user2Flower: String,
)

package model.challenge

/**
 * @Created by 김현국 2023/07/05
 */
data class ChallengeDomainModel(
    val challengeNo: Int,
    val endDate: String,
    val isApproved: Boolean,
    val isFinished: Boolean,
    val name: String,
    val startDate: String,
    val user1: UserDomainModel,
    val user1CommitCnt: Int,
    val user1Flower: String,
    val user2: UserDomainModel,
    val user2CommitCnt: Int,
    val user2Flower: String,
)

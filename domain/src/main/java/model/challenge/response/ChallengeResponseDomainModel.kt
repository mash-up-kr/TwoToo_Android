package model.challenge.response

/**
 * @Created by 김현국 2023/07/05
 */
data class ChallengeResponseDomainModel(
    val challengeNo: Int,
    val endDate: String,
    val description: String = "",
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
) {
    companion object {

        val default = ChallengeResponseDomainModel(
            challengeNo = 1,
            startDate = "2023-07-10T09:26:08.522Z",
            endDate = "2023-08-01T09:26:08.522Z",
            isApproved = false,
            isFinished = false,
            name = "테스트용",
            user1 = UserResponseDomainModel.user1,
            user1CommitCnt = 1,
            user1Flower = "SUNFLOWER",
            user2 = UserResponseDomainModel.user2,
            user2CommitCnt = 1,
            user2Flower = "TULIP",
        )
    }
}

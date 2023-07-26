package model.challenge.response

/**
 * @Created by 김현국 2023/07/05
 */
data class UserResponseDomainModel(
    val nickname: String,
    val partnerNo: Int,
    val userNo: Int,
) {
    companion object {
        val user1 = UserResponseDomainModel(
            nickname = "유저1",
            partnerNo = 2,
            userNo = 1,
        )
        val user2 = UserResponseDomainModel(
            nickname = "유저2",
            partnerNo = 1,
            userNo = 2,
        )
    }
}

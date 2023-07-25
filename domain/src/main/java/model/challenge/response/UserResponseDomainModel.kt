package model.challenge.response

/**
 * @Created by 김현국 2023/07/05
 */
data class UserResponseDomainModel(
//    val deviceToken: String,
//    val loginType: String,
    val nickname: String,
    val partnerNo: Int?,
//    val socialId: String,
    val userNo: Int,
) {
    companion object {
        val user1 = UserResponseDomainModel(
//            deviceToken = "FCM TOKEN",
//            loginType = "Kakao",
            nickname = "유저1",
            partnerNo = 2,
//            socialId = "user1@test.com",
            userNo = 1,
        )
        val user2 = UserResponseDomainModel(
//            deviceToken = "FCM TOKEN",
//            loginType = "Kakao",
            nickname = "유저2",
            partnerNo = 1,
//            socialId = "user2@test.com",
            userNo = 2,
        )
    }
}

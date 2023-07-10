package model.challenge.response

/**
 * @Created by 김현국 2023/07/05
 */
data class UserResponseDomainModel(
    val deviceToken: String,
    val loginType: String,
    val nickname: String,
    val partnerNo: Int,
    val socialId: String,
    val userNo: Int,
)

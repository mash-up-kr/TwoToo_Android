package model.user

data class UserAuthResponseDomainModel(
    val socialId: String? = "",
    val loginType: String = "",
    val deviceToken: String = "",
    val accessToken: String = "",
    val state: String = "",
    val userNo: Int = 0,
    val nickname: String? = "",
    val partnerNo: Int? = 0
)


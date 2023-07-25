package model.user

open class UserInfoDomainModel(
    val userNo: Int = 0,
    val nickname: String = "",
    val partnerNo: Int? = null,
    val partnerNickname: String = "",
    val totalChallengeCount: Int = 0,
)

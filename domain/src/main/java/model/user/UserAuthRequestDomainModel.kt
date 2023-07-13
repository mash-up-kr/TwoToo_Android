package model.user

data class UserAuthRequestDomainModel(
    val socialId: String = "",
    val loginType: String = "Kakao",
    val deviceToken: String = ""
)

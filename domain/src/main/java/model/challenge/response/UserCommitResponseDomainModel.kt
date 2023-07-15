package model.challenge.response

/**
 * @Created by 김현국 2023/07/05
 */
data class UserCommitResponseDomainModel(
    val commitNo: Int,
    val partnerComment: String,
    val photoUrl: String,
    val text: String,
    val userNo: Int,
) {
    companion object {
        val default = UserCommitResponseDomainModel(
            commitNo = 1,
            partnerComment = "고생했다",
            photoUrl = "",
            text = "오늘의 인증텍스트",
            userNo = 1,
        )
    }
}

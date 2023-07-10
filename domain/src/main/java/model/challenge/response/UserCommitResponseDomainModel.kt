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
)

package model.challenge

/**
 * @Created by 김현국 2023/07/05
 */
data class UserCommitDomainModel(
    val commitNo: Int,
    val partnerComment: String,
    val photoUrl: String,
    val text: String,
    val userNo: Int,
)

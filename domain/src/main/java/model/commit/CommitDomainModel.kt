package model.commit

/**
 * @Created by 김현국 2023/07/10
 */
data class CommitDomainModel(
    val commitNo: Int,
    val userNo: Int,
    val text: String,
    val photoUrl: String,
    val partnerComment: String,
)

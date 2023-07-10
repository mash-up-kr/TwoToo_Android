package model.commit


data class CommitDomainModel(
    val commitNo: Int,
    val userNo: Int,
    val text: String,
    val photoUrl: String,
    val partnerComment: String,
)

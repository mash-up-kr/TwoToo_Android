package model.commit.response


data class CommitResponseDomainModel(
    val commitNo: Int,
    val userNo: Int,
    val text: String,
    val photoUrl: String,
    val partnerComment: String,
)

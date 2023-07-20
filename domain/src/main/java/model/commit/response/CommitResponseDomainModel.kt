package model.commit.response

// TODO replace mapper in model
data class CommitResponseDomainModel(
    val commitNo: Int,
    val userNo: Int,
    val text: String,
    val photoUrl: String,
    val partnerComment: String,
)

package model.challenge.response

data class HistoryChallengeDomainModel(
    val viewState: String? = "InProgress",
    val challengeNo: Int,
    val name: String,
    val description: String? = "",
    val startDate: String,
    val endDate: String,
    val user1No: Int? = 1,
    val user2No: Int? = 2,
    val user1CommitCnt: Int,
    val user2CommitCnt: Int,
    val user1Flower: String,
    val user2Flower: String,
)

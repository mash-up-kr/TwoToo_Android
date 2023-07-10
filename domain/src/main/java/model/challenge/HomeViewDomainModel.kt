package model.challenge


data class HomeViewDomainModel(
    val challengeTotal: Int,
    val onGoingChallenge: ChallengeDomainModel,
    val user1Commit: UserCommitDomainModel,
    val user2Commit: UserCommitDomainModel,
    val viewState: String,
)

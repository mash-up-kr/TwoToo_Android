package model.challenge.response


data class HomeViewResponseDomainModel(
    val challengeTotal: Int,
    val onGoingChallenge: ChallengeResponseDomainModel,
    val user1Commit: UserCommitResponseDomainModel,
    val user2Commit: UserCommitResponseDomainModel,
    val viewState: String,
)

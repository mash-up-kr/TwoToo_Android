package model.challenge

/**
 * @Created by 김현국 2023/07/04
 */
data class HomeViewDomainModel(
    val challengeTotal: Int,
    val onGoingChallenge: ChallengeDomainModel,
    val user1Commit: UserCommitDomainModel,
    val user2Commit: UserCommitDomainModel,
    val viewState: String,
)

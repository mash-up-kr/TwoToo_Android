package model.challenge.response

data class HomeViewResponseDomainModel(
    val challengeTotal: Int,
    val onGoingChallenge: ChallengeResponseDomainModel,
    val user1Commit: UserCommitResponseDomainModel?,
    val user2Commit: UserCommitResponseDomainModel?,
    val viewState: String,
) {
    companion object {

        val default = HomeViewResponseDomainModel(
            viewState = "BEFORE_CREATE",
            onGoingChallenge = ChallengeResponseDomainModel.default,
            user1Commit = UserCommitResponseDomainModel.default,
            user2Commit = UserCommitResponseDomainModel.default.copy(
                userNo = 2,
            ),
            challengeTotal = 1,
        )

        val beforeCreate = default
        val beforeMyApprove = default.copy(
            viewState = "BEFORE_MY_APPROVE",
        )
        val beforePartnerApprove = default.copy(
            viewState = "BEFORE_PARTNER_APPROVE",
        )
        val expiredByNotApproved = default.copy(
            viewState = "EXPIRED_BY_NOT_APPROVED",
        )
        val approvedButBeforeStartDate = default.copy(
            viewState = "APPROVED_BUT_BEFORE_START_DATE",
        )
        val inProgress = default.copy(
            viewState = "IN_PROGRESS",
        )
        val complete = default.copy(
            viewState = "COMPLETE",
        )
    }
}

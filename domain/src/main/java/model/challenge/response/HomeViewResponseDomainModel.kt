package model.challenge.response

data class HomeViewResponseDomainModel(
    val viewState: String,
    val challengeTotal: Int,
    val onGoingChallenge: ChallengeResponseDomainModel?,
    val myInfo: UserResponseDomainModel,
    val myCommit: UserCommitResponseDomainModel?,
    val partnerInfo: UserResponseDomainModel,
    val partnerCommit: UserCommitResponseDomainModel?,
    val myStingCnt: Int,
) {
    companion object {

        val default = HomeViewResponseDomainModel(
            viewState = "BEFORE_CREATE",
            onGoingChallenge = ChallengeResponseDomainModel.default,
            myInfo = UserResponseDomainModel.user1,
            myCommit = UserCommitResponseDomainModel.default,
            partnerInfo = UserResponseDomainModel.user2,
            partnerCommit = UserCommitResponseDomainModel.default.copy(
                userNo = 2,
            ),
            challengeTotal = 1,
            myStingCnt = 0,
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

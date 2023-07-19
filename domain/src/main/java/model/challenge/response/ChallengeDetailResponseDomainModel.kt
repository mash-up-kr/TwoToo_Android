package model.challenge.response

import model.commit.response.CommitResponseDomainModel

data class ChallengeDetailResponseDomainModel(
    val challengeResponseDomainModel: ChallengeResponseDomainModel,
    val myCommitResponseDomainModel: List<CommitResponseDomainModel>,
    val partnerCommitResponseDomainModel: List<CommitResponseDomainModel>,
)

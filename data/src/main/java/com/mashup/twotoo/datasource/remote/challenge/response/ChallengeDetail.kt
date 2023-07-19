package com.mashup.twotoo.datasource.remote.challenge.response

import com.mashup.twotoo.datasource.remote.commit.response.Commit
import com.mashup.twotoo.mapper.toDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import model.challenge.response.ChallengeDetailResponseDomainModel

@JsonClass(generateAdapter = true)
data class ChallengeDetail(
    val challenge: Challenge,
    @Json(name = "user1CommitList")val user1CommitList: List<Commit>,
    @Json(name = "user2CommitList") val user2CommitList: List<Commit>,
)

fun ChallengeDetail.toDomainModel(): ChallengeDetailResponseDomainModel {
    return ChallengeDetailResponseDomainModel(
        challengeResponseDomainModel = this.challenge.toDomainModel(),
        myCommitResponseDomainModel = this.user1CommitList.map { it.toDomainModel() },
        partnerCommitResponseDomainModel = this.user2CommitList.map { it.toDomainModel() },
    )
}

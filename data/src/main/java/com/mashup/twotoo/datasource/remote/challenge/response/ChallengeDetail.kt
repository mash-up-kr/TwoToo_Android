package com.mashup.twotoo.datasource.remote.challenge.response

import com.mashup.twotoo.datasource.remote.commit.response.Commit
import com.mashup.twotoo.mapper.toDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import model.challenge.response.ChallengeDetailResponseDomainModel
import model.challenge.response.ChallengeResponseDomainModel

@JsonClass(generateAdapter = true)
data class ChallengeDetail(
    @Json(name = "challengeNo") val challengeNo: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "user1") val user1: User,
    @Json(name = "user2") val user2: User,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "endDate") val endDate: String,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "user1CommitCnt") val user1CommitCnt: Int,
    @Json(name = "user2CommitCnt") val user2CommitCnt: Int,
    @Json(name = "user1Flower") val user1Flower: String,
    @Json(name = "user2Flower") val user2Flower: String,
    @Json(name = "isApproved") val isApproved: Boolean,
    @Json(name = "isFinished") val isFinished: Boolean,
    @Json(name = "user1CommitList") val user1CommitList: List<Commit>,
    @Json(name = "user2CommitList") val user2CommitList: List<Commit>,
)
fun ChallengeDetail.toChallengeResponseDomainMode(): ChallengeResponseDomainModel {
    return ChallengeResponseDomainModel(
        challengeNo = this.challengeNo,
        endDate = this.endDate,
        description = this.description,
        isApproved = this.isApproved,
        isFinished = this.isFinished,
        name = this.name,
        startDate = this.startDate,
        user1 = this.user1.toDomainModel(),
        user1CommitCnt = this.user1CommitCnt,
        user1Flower = this.user1Flower,
        user2 = this.user2.toDomainModel(),
        user2CommitCnt = this.user2CommitCnt,
        user2Flower = this.user2Flower,
    )
}
fun ChallengeDetail.toDomainModel(): ChallengeDetailResponseDomainModel {
    return ChallengeDetailResponseDomainModel(
        challengeResponseDomainModel = this.toChallengeResponseDomainMode(),
        myCommitResponseDomainModel = this.user1CommitList.map { it.toDomainModel() },
        partnerCommitResponseDomainModel = this.user2CommitList.map { it.toDomainModel() },
    )
}

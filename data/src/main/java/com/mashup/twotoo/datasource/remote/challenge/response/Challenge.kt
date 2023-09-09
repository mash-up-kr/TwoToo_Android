package com.mashup.twotoo.datasource.remote.challenge.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import model.challenge.response.HistoryChallengeDomainModel

@JsonClass(generateAdapter = true)
data class Challenge(
    @Json(name = "viewState") val viewState: String? = "InProgress",
    @Json(name = "challengeNo") val challengeNo: Int,
    @Json(name = "name") val name: String,
    @Json(name = "user1") val user1: User? = null,
    @Json(name = "user2") val user2: User? = null,
    @Json(name = "user1No") val user1No: Int? = 1,
    @Json(name = "user2No") val user2No: Int? = 2,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "endDate") val endDate: String,
    @Json(name = "user1CommitCnt") val user1CommitCnt: Int,
    @Json(name = "user2CommitCnt") val user2CommitCnt: Int,
    @Json(name = "user1Flower") val user1Flower: String,
    @Json(name = "user2Flower") val user2Flower: String,
    @Json(name = "isApproved") val isApproved: Boolean = true,
    @Json(name = "isFinished") val isFinished: Boolean = false,
    @Json(name = "description") val description: String? = "",
) {
    companion object {
        fun Challenge.toHistoryChallengeDomainModel(): HistoryChallengeDomainModel =
            HistoryChallengeDomainModel(
                viewState = this.viewState,
                challengeNo = this.challengeNo,
                name = this.name,
                description = this.description,
                startDate = this.startDate,
                endDate = this.endDate,
                user1No = this.user1No,
                user2No = this.user2No,
                user1CommitCnt = this.user1CommitCnt,
                user2CommitCnt = this.user2CommitCnt,
                user1Flower = this.user1Flower,
                user2Flower = this.user2Flower,
            )
    }
}

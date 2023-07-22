package com.mashup.twotoo.datasource.remote.challenge.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Challenge(
    @Json(name = "challengeNo") val challengeNo: Int,
    @Json(name = "name") val name: String,
    @Json(name = "user1") val user1: User,
    @Json(name = "user2") val user2: User,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "endDate") val endDate: String,
    @Json(name = "user1CommitCnt") val user1CommitCnt: Int,
    @Json(name = "user2CommitCnt") val user2CommitCnt: Int,
    @Json(name = "user1Flower") val user1Flower: String,
    @Json(name = "user2Flower") val user2Flower: String,
    @Json(name = "isApproved") val isApproved: Boolean,
    @Json(name = "isFinished") val isFinished: Boolean,
)

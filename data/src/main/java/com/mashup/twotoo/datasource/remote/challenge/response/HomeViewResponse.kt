package com.mashup.twotoo.datasource.remote.challenge.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeViewResponse(
    @Json(name = "challengeTotal") val challengeTotal: Int,
    @Json(name = "onGoingChallenge") val onGoingChallenge: Challenge,
    @Json(name = "user1Commit") val user1Commit: UserCommit,
    @Json(name = "user2Commit") val user2Commit: UserCommit,
    @Json(name = "viewState") val viewState: String,
)

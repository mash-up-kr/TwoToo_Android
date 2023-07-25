package com.mashup.twotoo.datasource.remote.view.response

import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.datasource.remote.challenge.response.User
import com.mashup.twotoo.datasource.remote.challenge.response.UserCommit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ViewHomeResponse(
    @Json(name = "challengeTotal") val challengeTotal: Int,
    @Json(name = "onGoingChallenge") val onGoingChallenge: Challenge?,
    @Json(name = "user1") val user1: User,
    @Json(name = "user1Commit") val user1Commit: UserCommit?,
    @Json(name = "user2") val user2: User,
    @Json(name = "user2Commit") val user2Commit: UserCommit?,
    @Json(name = "viewState") val viewState: String,
    @Json(name = "userStingCnt") val userStingCnt: Int,
)

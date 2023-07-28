package com.mashup.twotoo.datasource.remote.view.response

import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.datasource.remote.challenge.response.User
import com.mashup.twotoo.datasource.remote.challenge.response.UserCommit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ViewHomeResponse(
    @Json(name = "viewState") val viewState: String,
    @Json(name = "challengeTotal") val challengeTotal: Int,
    @Json(name = "onGoingChallenge") val onGoingChallenge: Challenge?,
    @Json(name = "myInfo") val myInfo: User,
    @Json(name = "myCommit") val myCommit: UserCommit?,
    @Json(name = "partnerInfo") val partnerInfo: User,
    @Json(name = "partnerCommit") val partnerCommit: UserCommit?,
    @Json(name = "myStingCnt") val myStingCnt: Int,
)

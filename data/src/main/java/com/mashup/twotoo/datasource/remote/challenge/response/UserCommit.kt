package com.mashup.twotoo.datasource.remote.challenge.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserCommit(
    @Json(name = "commitNo") val commitNo: Int,
    @Json(name = "partnerComment") val partnerComment: String,
    @Json(name = "photoUrl") val photoUrl: String,
    @Json(name = "text") val text: String,
    @Json(name = "userNo") val userNo: Int,
)

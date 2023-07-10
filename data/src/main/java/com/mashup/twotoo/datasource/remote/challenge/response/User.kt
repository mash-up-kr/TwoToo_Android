package com.mashup.twotoo.datasource.remote.challenge.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "deviceToken") val deviceToken: String,
    @Json(name = "loginType") val loginType: String,
    @Json(name = "nickname") val nickname: String,
    @Json(name = "partnerNo") val partnerNo: Int,
    @Json(name = "socialId") val socialId: String,
    @Json(name = "userNo") val userNo: Int,
)

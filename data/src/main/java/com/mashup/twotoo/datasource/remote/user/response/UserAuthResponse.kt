package com.mashup.twotoo.datasource.remote.user.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAuthResponse(
    @Json(name = "userNo") val userNo: Int = 0,
    @Json(name = "nickname") val nickname: String? = "",
    @Json(name = "partnerNo") val partnerNo: Int? = 0,
    @Json(name = "socialId") val socialId: String?,
    @Json(name = "loginType") val loginType: String,
    @Json(name = "deviceToken") val deviceToken: String,
    @Json(name = "state") val state: String,
    @Json(name = "accessToken") val accessToken: String,
)

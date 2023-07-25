package com.mashup.twotoo.datasource.remote.challenge.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "nickname") val nickname: String,
    @Json(name = "partnerNo") val partnerNo: Int?,
    @Json(name = "userNo") val userNo: Int,
)

package com.mashup.twotoo.datasource.remote.user.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class UserInfoResponse(
    @Json(name = "userNo") val userNo: Int = 0,
    @Json(name = "nickname") val nickname: String = "",
    @Json(name = "partnerNo") val partnerNo: Int? = 0
)

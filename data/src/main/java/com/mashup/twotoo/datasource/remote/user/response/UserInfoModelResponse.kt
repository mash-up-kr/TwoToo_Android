package com.mashup.twotoo.datasource.remote.user.response

import com.squareup.moshi.Json

data class UserInfoModelResponse(
    @Json(name = "userNo") val userNo: Int,
    @Json(name = "nickname") val nickname: String,
    @Json(name = "partnerNo") val partnerNo: Int
)

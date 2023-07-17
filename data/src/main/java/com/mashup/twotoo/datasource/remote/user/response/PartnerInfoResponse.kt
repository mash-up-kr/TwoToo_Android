package com.mashup.twotoo.datasource.remote.user.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PartnerInfoResponse(
    @Json(name = "partnerNo") val partnerNo: Int,
)
package com.mashup.twotoo.datasource.remote.user.response

import com.squareup.moshi.Json

data class PartnerInfoResponse(
    @Json(name = "partnerNo") val partnerNo: Int,
)

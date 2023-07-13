package com.mashup.twotoo.datasource.remote.user.request

data class UserNickNameRequest(
    val nickname: String,
    val partnerNo: Int? = null
)

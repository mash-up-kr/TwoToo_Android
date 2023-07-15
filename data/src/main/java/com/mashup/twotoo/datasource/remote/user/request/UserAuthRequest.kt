package com.mashup.twotoo.datasource.remote.user.request

data class UserAuthRequest(
    val socialId: String,
    val loginType: String,
    val deviceToken: String,
)

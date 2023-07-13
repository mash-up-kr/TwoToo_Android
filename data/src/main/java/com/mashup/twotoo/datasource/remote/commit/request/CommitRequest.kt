package com.mashup.twotoo.datasource.remote.commit.request

import okhttp3.MultipartBody

data class CommitRequest(
    val text: MultipartBody.Part,
    val img: MultipartBody.Part,
)

package com.mashup.twotoo.datasource.remote.view

import com.mashup.twotoo.datasource.remote.view.response.ViewHomeResponse
import retrofit2.http.GET
import util.NetworkResult

interface ViewApi {
    @GET("/view/home")
    suspend fun getViewHome(): NetworkResult<ViewHomeResponse>
}

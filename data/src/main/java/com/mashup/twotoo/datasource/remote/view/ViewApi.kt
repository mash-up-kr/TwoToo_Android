package com.mashup.twotoo.datasource.remote.view

import com.mashup.twotoo.datasource.remote.view.response.ViewHomeResponse
import retrofit2.http.GET

/**
 * @Created by 김현국 2023/07/06
 */
interface ViewApi {
    @GET("/view/home")
    suspend fun getViewHome(): ViewHomeResponse
}

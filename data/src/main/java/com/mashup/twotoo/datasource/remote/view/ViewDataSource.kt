package com.mashup.twotoo.datasource.remote.view

import com.mashup.twotoo.datasource.remote.view.response.ViewHomeResponse
import javax.inject.Inject


class ViewDataSource @Inject constructor(
    private val viewApi: ViewApi,
) {
    suspend fun getViewHome(): ViewHomeResponse {
        return viewApi.getViewHome()
    }
}

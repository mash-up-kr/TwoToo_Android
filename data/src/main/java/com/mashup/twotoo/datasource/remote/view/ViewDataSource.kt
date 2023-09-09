package com.mashup.twotoo.datasource.remote.view

import com.mashup.twotoo.datasource.remote.view.response.ViewHomeResponse
import util.NetworkResult
import javax.inject.Inject

class ViewDataSource @Inject constructor(
    private val viewApi: ViewApi,
) {
    suspend fun getViewHome(): NetworkResult<ViewHomeResponse> {
        return viewApi.getViewHome()
    }
}

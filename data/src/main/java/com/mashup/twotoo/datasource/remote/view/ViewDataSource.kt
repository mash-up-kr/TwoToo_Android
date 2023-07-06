package com.mashup.twotoo.datasource.remote.view

import com.mashup.twotoo.datasource.remote.view.response.ViewHomeResponse
import javax.inject.Inject

/**
 * @Created by 김현국 2023/07/06
 */
class ViewDataSource @Inject constructor(
    private val viewApi: ViewApi,
) {
    suspend fun getViewHome(): ViewHomeResponse {
        return viewApi.getViewHome()
    }
}

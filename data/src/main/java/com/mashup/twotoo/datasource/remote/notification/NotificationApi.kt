package com.mashup.twotoo.datasource.remote.notification

import com.mashup.twotoo.datasource.remote.notification.request.NotificationRequest
import com.mashup.twotoo.datasource.remote.notification.response.Notification
import retrofit2.http.Body
import retrofit2.http.POST
import util.NetworkResult

interface NotificationApi {

    @POST("/notification/sting")
    suspend fun sting(
        @Body notificationRequest: NotificationRequest,
    ): NetworkResult<Notification>
}

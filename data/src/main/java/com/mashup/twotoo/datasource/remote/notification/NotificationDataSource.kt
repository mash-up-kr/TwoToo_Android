package com.mashup.twotoo.datasource.remote.notification

import com.mashup.twotoo.datasource.remote.notification.request.NotificationRequest
import com.mashup.twotoo.datasource.remote.notification.response.Notification
import util.NetworkResult
import javax.inject.Inject

class NotificationDataSource @Inject constructor(
    private val notificationApi: NotificationApi,
) {
    suspend fun sting(
        notificationRequest: NotificationRequest,
    ): NetworkResult<Notification> {
        return notificationApi.sting(notificationRequest)
    }
}

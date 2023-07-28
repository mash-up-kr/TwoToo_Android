package com.mashup.twotoo.mapper

import com.mashup.twotoo.datasource.remote.notification.request.NotificationRequest
import com.mashup.twotoo.datasource.remote.notification.response.Notification
import model.notification.request.NotificationRequestDomainModel
import model.notification.response.NotificationResponseDomainModel

fun NotificationRequestDomainModel.toDataModel(): NotificationRequest {
    return NotificationRequest(
        message = this.message,
    )
}

fun Notification.toDomainModel(): NotificationResponseDomainModel {
    return NotificationResponseDomainModel(
        userNo = this.userNo,
        message = this.message,
    )
}

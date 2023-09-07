package com.mashup.twotoo.presenter.repository

import model.notification.request.NotificationRequestDomainModel
import model.notification.response.NotificationResponseDomainModel
import repository.NotificationRepository

class FakeNotificationRepository : NotificationRepository {
    override suspend fun sting(notificationRequestDomainModel: NotificationRequestDomainModel): Result<NotificationResponseDomainModel> {
        TODO("Not yet implemented")
    }
}

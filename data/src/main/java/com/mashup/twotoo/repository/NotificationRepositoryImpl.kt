package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.notification.NotificationDataSource
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.notification.request.NotificationRequestDomainModel
import model.notification.response.NotificationResponseDomainModel
import repository.NotificationRepository
import util.NetworkResult
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDataSource: NotificationDataSource,
) : NotificationRepository {
    override suspend fun sting(notificationRequestDomainModel: NotificationRequestDomainModel): NetworkResult<NotificationResponseDomainModel> {
        return notificationDataSource.sting(notificationRequest = notificationRequestDomainModel.toDataModel()).map { notification ->
            notification.toDomainModel()
        }
    }
}

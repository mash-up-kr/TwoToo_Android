package repository

import model.notification.request.NotificationRequestDomainModel
import model.notification.response.NotificationResponseDomainModel
import util.NetworkResult

interface NotificationRepository {
    suspend fun sting(
        notificationRequestDomainModel: NotificationRequestDomainModel,
    ): NetworkResult<NotificationResponseDomainModel>
}

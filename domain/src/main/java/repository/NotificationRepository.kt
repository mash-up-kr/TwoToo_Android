package repository

import model.notification.request.NotificationRequestDomainModel
import model.notification.response.NotificationResponseDomainModel

interface NotificationRepository {
    suspend fun sting(
        notificationRequestDomainModel: NotificationRequestDomainModel,
    ): Result<NotificationResponseDomainModel>
}

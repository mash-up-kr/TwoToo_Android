package usecase.notification

import model.notification.request.NotificationRequestDomainModel
import model.notification.response.NotificationResponseDomainModel
import repository.NotificationRepository
import util.NetworkResult
import javax.inject.Inject

class StingUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(notificationRequestDomainModel: NotificationRequestDomainModel): NetworkResult<NotificationResponseDomainModel> {
        return notificationRepository.sting(notificationRequestDomainModel = notificationRequestDomainModel)
    }
}

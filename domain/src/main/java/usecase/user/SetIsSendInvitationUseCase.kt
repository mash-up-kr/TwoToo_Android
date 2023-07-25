package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class SetIsSendInvitationUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(isSend: Boolean) {
        userDataStoreRepository.setIsSendInvitation(isSend)
    }
}

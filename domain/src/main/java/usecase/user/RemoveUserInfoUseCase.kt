package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class RemoveUserInfoUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke() {
        userDataStoreRepository.removeUserInfo()
    }
}

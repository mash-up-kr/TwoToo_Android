package usecase.user

import model.user.UserAuthResponseDomainModel
import repository.UserDataStoreRepository
import javax.inject.Inject

class GetPreferenceUserInfoUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(): UserAuthResponseDomainModel? {
        return userDataStoreRepository.getUserInfo()
    }
}

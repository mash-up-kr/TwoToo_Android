package usecase.user

import model.user.UserAuthResponseDomainModel
import repository.UserDataStoreRepository
import javax.inject.Inject

class SetPreferenceUserInfoUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(userAuthResponseDomainModel: UserAuthResponseDomainModel) {
        userDataStoreRepository.setUserInfo(userAuthResponseDomainModel)
    }
}

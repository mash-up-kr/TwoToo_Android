package usecase.token

import repository.UserDataStoreRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(): String {
        return userDataStoreRepository.getAccessToken()
    }
}

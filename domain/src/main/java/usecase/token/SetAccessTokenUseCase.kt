package usecase.token

import repository.UserDataStoreRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(accessToken: String) {
        userDataStoreRepository.setAccessToken(accessToken)
    }
}

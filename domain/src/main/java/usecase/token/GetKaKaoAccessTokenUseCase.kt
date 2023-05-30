package usecase.token

import repository.UserDataStoreRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetKaKaoAccessTokenUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(): String {
        return userDataStoreRepository.getKaKaoAccessToken()
    }
}

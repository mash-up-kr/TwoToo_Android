package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class GetUserNoUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(): Int {
        return userDataStoreRepository.getUserNo()
    }
}

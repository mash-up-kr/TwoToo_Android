package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class SetUserNoUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(userNo: Int) {
        userDataStoreRepository.setUserNo(userNo)
    }
}

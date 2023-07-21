package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class GetVisibilityCompleteDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke(): Boolean {
        return userDataStoreRepository.getVisibilityCompleteDialog()
    }
}

package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class RemoveVisibilityCompleteDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke() {
        userDataStoreRepository.removeVisibilityCompleteDialog()
    }
}

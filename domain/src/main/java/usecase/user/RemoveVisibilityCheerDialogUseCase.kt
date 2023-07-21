package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class RemoveVisibilityCheerDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke() {
        userDataStoreRepository.removeVisibilityCheerDialog()
    }
}

package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject
// Never used anywhere
class RemoveVisibilityCheerDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke() {
        userDataStoreRepository.removeVisibilityCheerDialog()
    }
}

package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

// Never used anywhere
class GetVisibilityCheerDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke(): Boolean {
        return userDataStoreRepository.getVisibilityCheerDialog()
    }
}

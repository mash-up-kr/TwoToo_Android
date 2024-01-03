package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

// Not Used Anywhere
class SetVisibilityCheerDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke(visibility: Boolean) {
        userDataStoreRepository.setVisibilityCheerDialog(visibility = visibility)
    }
}

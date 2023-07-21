package usecase.user

import repository.UserDataStoreRepository
import javax.inject.Inject

class SetVisibilityCompleteDialogUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) {
    suspend operator fun invoke(visibility: Boolean) {
        userDataStoreRepository.setVisibilityCompleteDialog(visibility = visibility)
    }
}

package usecase.user

import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class DeletePartnerUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResult<Boolean> {
        return userRepository.deletePartner()
    }
}

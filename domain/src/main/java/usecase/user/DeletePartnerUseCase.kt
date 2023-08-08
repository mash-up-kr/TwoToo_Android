package usecase.user

import repository.UserRepository
import javax.inject.Inject

class DeletePartnerUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return userRepository.deletePartner()
    }
}

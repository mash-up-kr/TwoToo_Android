package usecase.user

import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResult<Boolean> {
        return userRepository.signOut()
    }
}

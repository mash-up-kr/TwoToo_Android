package usecase.user

import model.user.UserInfoDomainModel
import repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<UserInfoDomainModel> {
        return userRepository.getUserInfo()
    }
}

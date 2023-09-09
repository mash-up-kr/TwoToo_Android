package usecase.user

import util.NetworkResult
import model.user.UserInfoDomainModel
import repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResult<UserInfoDomainModel> {
        return userRepository.getUserInfo()
    }
}

package usecase.user

import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel
import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class SetNickNameUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userNickNameDomainModel: UserNickNameDomainModel): NetworkResult<UserInfoDomainModel> {
        return userRepository.setUserNickName(userNickNameDomainModel)
    }
}

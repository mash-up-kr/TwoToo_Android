package usecase.user

import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel
import repository.UserRepository
import javax.inject.Inject

class SetNickNameUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userNickNameDomainModel: UserNickNameDomainModel): Result<UserInfoDomainModel> {
        return userRepository.setUserNickName(userNickNameDomainModel)
    }
}

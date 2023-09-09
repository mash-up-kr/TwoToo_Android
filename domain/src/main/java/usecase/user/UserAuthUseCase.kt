package usecase.user

import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class UserAuthUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userAuthRequestDomainModel: UserAuthRequestDomainModel): NetworkResult<UserAuthResponseDomainModel> {
        return userRepository.userAuthorize(userAuthRequestDomainModel)
    }
}

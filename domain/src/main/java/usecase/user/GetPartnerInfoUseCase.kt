package usecase.user

import model.user.PartnerInfoDomainModel
import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class GetPartnerInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResult<PartnerInfoDomainModel> {
        return userRepository.getPartnerInfo()
    }
}

package usecase.user

import model.user.PartnerInfoDomainModel
import repository.UserRepository
import javax.inject.Inject

class GetPartnerInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<PartnerInfoDomainModel> {
        return userRepository.getPartnerInfo()
    }
}

package usecase.challenge

import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import util.NetworkResult
import javax.inject.Inject

class GetAllChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(): NetworkResult<List<ChallengeResponseDomainModel>> {
        return challengeRepository.getAllChallenge()
    }
}

package usecase.challenge

import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

class GetAllChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(): Result<List<ChallengeResponseDomainModel>> {
        return challengeRepository.getAllChallenge()
    }
}

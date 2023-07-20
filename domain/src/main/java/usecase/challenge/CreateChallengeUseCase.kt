package usecase.challenge

import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

class CreateChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(createChallengeDomainModel: CreateChallengeRequestDomainModel): Result<ChallengeResponseDomainModel> {
        return challengeRepository.createChallenge(createChallengeDomainModel)
    }
}

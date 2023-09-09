package usecase.challenge

import model.challenge.request.CreateChallengeRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import util.NetworkResult
import javax.inject.Inject

class CreateChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(createChallengeDomainModel: CreateChallengeRequestDomainModel): NetworkResult<ChallengeResponseDomainModel> {
        return challengeRepository.createChallenge(createChallengeDomainModel)
    }
}

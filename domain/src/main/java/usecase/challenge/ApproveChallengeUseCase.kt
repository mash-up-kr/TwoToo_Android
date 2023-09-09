package usecase.challenge

import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import util.NetworkResult
import javax.inject.Inject

class ApproveChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel
    ): NetworkResult<ChallengeResponseDomainModel> {
        return challengeRepository.approveChallenge(challengeNoRequestDomainModel, approveChallengeRequestDomainModel)
    }
}

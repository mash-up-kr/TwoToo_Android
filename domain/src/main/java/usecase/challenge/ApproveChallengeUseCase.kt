package usecase.challenge

import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

class ApproveChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
        approveChallengeRequestDomainModel: ApproveChallengeRequestDomainModel
    ): Result<ChallengeResponseDomainModel> {
        return challengeRepository.approveChallenge(challengeNoRequestDomainModel, approveChallengeRequestDomainModel)
    }
}

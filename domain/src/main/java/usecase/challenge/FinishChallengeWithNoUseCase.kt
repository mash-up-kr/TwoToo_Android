package usecase.challenge

import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

class FinishChallengeWithNoUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): Result<ChallengeResponseDomainModel> {
        return challengeRepository.finishChallengeWithNo(
            challengeNoRequestDomainModel = challengeNoRequestDomainModel,
        )
    }
}

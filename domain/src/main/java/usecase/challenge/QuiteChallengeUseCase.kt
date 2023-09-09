package usecase.challenge

import model.challenge.request.ChallengeNoRequestDomainModel
import repository.ChallengeRepository
import util.NetworkResult
import javax.inject.Inject

class QuiteChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): NetworkResult<Int> {
        return challengeRepository.quitChallenge(
            challengeNoRequestDomainModel = challengeNoRequestDomainModel,
        )
    }
}

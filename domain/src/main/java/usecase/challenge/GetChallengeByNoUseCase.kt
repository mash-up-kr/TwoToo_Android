package usecase.challenge

import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.response.ChallengeDetailResponseDomainModel
import repository.ChallengeRepository
import util.NetworkResult
import javax.inject.Inject

class GetChallengeByNoUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(
        challengeNoRequestDomainModel: ChallengeNoRequestDomainModel,
    ): NetworkResult<ChallengeDetailResponseDomainModel> {
        return challengeRepository.getChallengeByNo(
            challengeNoRequestDomainModel = challengeNoRequestDomainModel,
        )
    }
}

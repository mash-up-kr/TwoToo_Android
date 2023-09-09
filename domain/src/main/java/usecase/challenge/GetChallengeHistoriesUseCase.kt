package usecase.challenge

import model.challenge.response.HistoryChallengeDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

class GetChallengeHistoriesUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(): Result<List<HistoryChallengeDomainModel>> {
        return challengeRepository.getChallengeHistories()
    }
}

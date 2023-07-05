package usecase.challenge

import model.challenge.HomeViewDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

/**
 * @Created by 김현국 2023/07/04
 */
class GetHomeViewChallengeStateUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository,
) {
    suspend operator fun invoke(): Result<HomeViewDomainModel> {
        return challengeRepository.getHomeViewState()
    }
}

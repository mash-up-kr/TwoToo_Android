package usecase.view

import model.challenge.response.HomeViewResponseDomainModel
import repository.ViewRepository
import util.NetworkResult
import javax.inject.Inject

class GetViewHomeUseCase @Inject constructor(
    private val viewRepository: ViewRepository,
) {
    suspend operator fun invoke(): NetworkResult<HomeViewResponseDomainModel> {
        return viewRepository.getViewHome()
    }
}

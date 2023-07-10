package usecase.view

import model.challenge.HomeViewDomainModel
import repository.ViewRepository
import javax.inject.Inject


class GetViewHomeUseCase @Inject constructor(
    private val viewRepository: ViewRepository,
) {
    suspend operator fun invoke(): Result<HomeViewDomainModel> {
        return viewRepository.getViewHome()
    }
}

package usecase.view

import model.challenge.HomeViewDomainModel
import repository.ViewRepository
import javax.inject.Inject

/**
 * @Created by 김현국 2023/07/04
 */
class GetViewHomeUseCase @Inject constructor(
    private val viewRepository: ViewRepository,
) {
    suspend operator fun invoke(): Result<HomeViewDomainModel> {
        return viewRepository.getViewHome()
    }
}

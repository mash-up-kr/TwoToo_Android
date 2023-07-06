package repository

import model.challenge.HomeViewDomainModel

/**
 * @Created by 김현국 2023/07/06
 */
interface ViewRepository {
    suspend fun getViewHome(): Result<HomeViewDomainModel>
}

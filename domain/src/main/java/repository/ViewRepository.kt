package repository

import model.challenge.HomeViewDomainModel


interface ViewRepository {
    suspend fun getViewHome(): Result<HomeViewDomainModel>
}

package repository

import model.challenge.response.HomeViewResponseDomainModel


interface ViewRepository {
    suspend fun getViewHome(): Result<HomeViewResponseDomainModel>
}

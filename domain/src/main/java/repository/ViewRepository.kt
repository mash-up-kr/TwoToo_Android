package repository

import model.challenge.response.HomeViewResponseDomainModel
import util.NetworkResult

interface ViewRepository {
    suspend fun getViewHome(): NetworkResult<HomeViewResponseDomainModel>
}

package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.view.ViewDataSource
import com.mashup.twotoo.mapper.toDomainModel
import model.challenge.response.HomeViewResponseDomainModel
import repository.ViewRepository
import util.NetworkResult
import javax.inject.Inject

class ViewRepositoryImpl @Inject constructor(
    private val viewDataSource: ViewDataSource,
) : ViewRepository {
    override suspend fun getViewHome(): NetworkResult<HomeViewResponseDomainModel> {
        return viewDataSource.getViewHome().map { viewHomeResponse ->
            viewHomeResponse.toDomainModel()
        }
    }
}

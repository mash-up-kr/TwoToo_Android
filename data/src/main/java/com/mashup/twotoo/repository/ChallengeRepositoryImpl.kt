package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.challenge.ChallengeDataSource
import com.mashup.twotoo.mapper.toDomainModel
import model.challenge.HomeViewDomainModel
import repository.ChallengeRepository
import javax.inject.Inject

/**
 * @Created by 김현국 2023/07/04
 */
class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource,
) : ChallengeRepository {
    override suspend fun createChallenge(): Result<HomeViewDomainModel> {
        return runCatching {
            // Todo change To createChallenge Api
            challengeDataSource.getHomeState().toDomainModel()
        }
    }

    override suspend fun approveChallenge() {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeViewState(): Result<HomeViewDomainModel> {
        return runCatching {
            challengeDataSource.getHomeState().toDomainModel()
        }
    }

    override suspend fun pushSting() {
        TODO("Not yet implemented")
    }
}

package com.mashup.twotoo.datasource.remote.challenge

import com.mashup.twotoo.datasource.remote.challenge.response.HomeViewResponse
import javax.inject.Inject

/**
 * @Created by 김현국 2023/07/04
 */
class ChallengeDataSource @Inject constructor(
    private val challengeApi: ChallengeApi,
) {
    suspend fun getHomeState(): HomeViewResponse {
        return challengeApi.getHomeState()
    }
}

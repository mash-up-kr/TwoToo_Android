package com.mashup.twotoo.datasource.remote.challenge

import com.mashup.twotoo.datasource.remote.challenge.request.ApproveChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.request.CreateChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import javax.inject.Inject


class ChallengeDataSource @Inject constructor(
    private val challengeApi: ChallengeApi,
) {
    suspend fun createChallenge(
        createChallengeRequest: CreateChallengeRequest,
    ): Challenge {
        return challengeApi.createChallenge(createChallengeRequest = createChallengeRequest)
    }

    suspend fun getAllChallenge(): List<Challenge> {
        return challengeApi.getAllChallenge()
    }

    suspend fun getChallengeByNo(
        challengeNo: Long,
    ): Challenge {
        return challengeApi.getChallengeByNo(challengeNo = challengeNo)
    }

    suspend fun deleteChallengeByNo(
        challengeNo: Long,
    ) {
        challengeApi.deleteChallengeByNo(challengeNo = challengeNo)
    }

    suspend fun approveChallengeWithNo(
        challengeNo: Long,
        approveChallengeRequest: ApproveChallengeRequest,
    ): Challenge {
        return challengeApi.approveChallengeWithNo(
            challengeNo = challengeNo,
            approveChallengeRequest = approveChallengeRequest,
        )
    }
}

package com.mashup.twotoo.datasource.remote.challenge

import com.mashup.twotoo.datasource.remote.challenge.request.ApproveChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.request.ChallengeNoRequest
import com.mashup.twotoo.datasource.remote.challenge.request.CreateChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.datasource.remote.challenge.response.ChallengeDetail
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
        challengeNoRequest: ChallengeNoRequest,
    ): ChallengeDetail {
        return challengeApi.getChallengeByNo(challengeNo = challengeNoRequest.challengeNo)
    }

    suspend fun deleteChallengeByNo(
        challengeNoRequest: ChallengeNoRequest,
    ): Int {
        return challengeApi.deleteChallengeByNo(challengeNo = challengeNoRequest.challengeNo)
    }

    suspend fun approveChallengeWithNo(
        challengeNoRequest: ChallengeNoRequest,
        approveChallengeRequest: ApproveChallengeRequest,
    ): Challenge {
        return challengeApi.approveChallengeWithNo(
            challengeNo = challengeNoRequest.challengeNo,
            approveChallengeRequest = approveChallengeRequest,
        )
    }
}

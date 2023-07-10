package com.mashup.twotoo.datasource.remote.challenge

import com.mashup.twotoo.datasource.remote.challenge.request.ApproveChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.request.CreateChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChallengeApi {

    @POST("/challenge")
    suspend fun createChallenge(
        @Body createChallengeRequest: CreateChallengeRequest,
    ): Challenge

    @GET("/challenge")
    suspend fun getAllChallenge(): List<Challenge>

    @GET("/challenge/{challengeNo}")
    suspend fun getChallengeByNo(@Path("challengeNo") challengeNo: Int): Challenge

    @DELETE("/challenge/{challengeNo}")
    suspend fun deleteChallengeByNo(@Path("challengeNo") challengeNo: Int): Int

    @POST("/challenge/{challengeNo}/approve")
    suspend fun approveChallengeWithNo(
        @Path("challengeNo") challengeNo: Int,
        @Body approveChallengeRequest: ApproveChallengeRequest,
    ): Challenge
}

package com.mashup.twotoo.datasource.remote.user

import com.mashup.twotoo.datasource.remote.user.response.PartnerInfoResponse
import com.mashup.twotoo.datasource.remote.user.response.UserInfoModelResponse
import com.mashup.twotoo.datasource.remote.user.request.UserNickNameRequest
import com.mashup.twotoo.datasource.remote.user.request.UserSignInRequest
import com.mashup.twotoo.datasource.remote.user.request.UserSignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("/user/signup")
    suspend fun signup(
        @Body userSignUp: UserSignUpRequest
    )

    @POST("/user/signin")
    suspend fun signIn(
        @Body userSignIn: UserSignInRequest
    )

    @POST("/user/nickname")
    suspend fun setUserNickName(
        @Body userNickName: UserNickNameRequest
    )

    @GET("/user/partner")
    suspend fun getUserPartnerInfo(): PartnerInfoResponse

    @GET("/user/me")
    suspend fun getUserInfo(): UserInfoModelResponse
}

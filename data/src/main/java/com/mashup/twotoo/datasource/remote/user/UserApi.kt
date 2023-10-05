package com.mashup.twotoo.datasource.remote.user

import com.mashup.twotoo.datasource.remote.user.request.UserAuthRequest
import com.mashup.twotoo.datasource.remote.user.request.UserNickNameRequest
import com.mashup.twotoo.datasource.remote.user.response.PartnerInfoResponse
import com.mashup.twotoo.datasource.remote.user.response.UserAuthResponse
import com.mashup.twotoo.datasource.remote.user.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import util.NetworkResult

interface UserApi {
    @POST("/user/authorize")
    suspend fun userAuthorize(
        @Body userAuthorize: UserAuthRequest
    ): NetworkResult<UserAuthResponse>

    @PATCH("/user/nickname")
    suspend fun setUserNickName(
        @Body userNickName: UserNickNameRequest
    ): NetworkResult<UserInfoResponse>

    @GET("/user/partner")
    suspend fun getUserPartnerInfo(): NetworkResult<PartnerInfoResponse>

    @GET("/user/me")
    suspend fun getUserInfo(): NetworkResult<UserInfoResponse>

    @PATCH("/user/delPartner")
    suspend fun deletePartner(): NetworkResult<Boolean>

    @DELETE("/user/signOut")
    suspend fun signOut(): NetworkResult<Boolean>

    @PATCH("/user/changeNickname")
    suspend fun changeNickName(
        @Body userNickName: UserNickNameRequest
    ): NetworkResult<UserInfoResponse>
}

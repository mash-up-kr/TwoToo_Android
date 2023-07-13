package com.mashup.twotoo.datasource.remote.user

import com.mashup.twotoo.datasource.remote.user.request.UserAuthRequest
import com.mashup.twotoo.datasource.remote.user.request.UserNickNameRequest
import com.mashup.twotoo.datasource.remote.user.response.PartnerInfoResponse
import com.mashup.twotoo.datasource.remote.user.response.UserAuthResponse
import com.mashup.twotoo.datasource.remote.user.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserApi {
    @POST("/user/authorize")
    suspend fun userAuthorize(
        @Body userAuthorize: UserAuthRequest
    ): UserAuthResponse

    @PATCH("/user/nickname")
    suspend fun setUserNickName(
        @Body userNickName: UserNickNameRequest
    ): UserInfoResponse

    @GET("/user/partner")
    suspend fun getUserPartnerInfo(): PartnerInfoResponse

    @GET("/user/me")
    suspend fun getUserInfo(): UserInfoResponse
}

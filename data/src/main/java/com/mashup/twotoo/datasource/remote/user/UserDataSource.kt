package com.mashup.twotoo.datasource.remote.user

import com.mashup.twotoo.datasource.remote.user.request.UserAuthRequest
import com.mashup.twotoo.datasource.remote.user.request.UserNickNameRequest
import com.mashup.twotoo.datasource.remote.user.response.PartnerInfoResponse
import com.mashup.twotoo.datasource.remote.user.response.UserAuthResponse
import com.mashup.twotoo.datasource.remote.user.response.UserInfoResponse
import util.NetworkResult
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserApi,
) {
    suspend fun userAuthorize(
        userAuthRequest: UserAuthRequest,
    ): NetworkResult<UserAuthResponse> {
        return userApi.userAuthorize(userAuthRequest)
    }

    suspend fun setUserNickName(
        userNickNameRequest: UserNickNameRequest,
    ): NetworkResult<UserInfoResponse> {
        return userApi.setUserNickName(userNickNameRequest)
    }

    suspend fun getPartnerInfo(): NetworkResult<PartnerInfoResponse> {
        return userApi.getUserPartnerInfo()
    }

    suspend fun getUserInfo(): NetworkResult<UserInfoResponse> {
        return userApi.getUserInfo()
    }

    suspend fun deletePartner(): NetworkResult<Boolean> {
        return userApi.deletePartner()
    }

    suspend fun signOut(): NetworkResult<Boolean> {
        return userApi.signOut()
    }
}

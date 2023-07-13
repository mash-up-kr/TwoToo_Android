package com.mashup.twotoo.datasource.remote.user

import com.mashup.twotoo.datasource.remote.user.request.UserAuthRequest
import com.mashup.twotoo.datasource.remote.user.request.UserNickNameRequest
import com.mashup.twotoo.datasource.remote.user.response.UserAuthResponse
import com.mashup.twotoo.datasource.remote.user.response.UserInfoResponse
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserApi,
) {
    suspend fun userAuthorize(
        userAuthRequest: UserAuthRequest,
    ): UserAuthResponse {
        return userApi.userAuthorize(userAuthRequest)
    }

    suspend fun setUserNickName(
        userNickNameRequest: UserNickNameRequest,
    ): UserInfoResponse {
        return userApi.setUserNickName(userNickNameRequest)
    }
}

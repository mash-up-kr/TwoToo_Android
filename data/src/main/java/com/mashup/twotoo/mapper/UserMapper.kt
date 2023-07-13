package com.mashup.twotoo.mapper

import com.mashup.twotoo.datasource.remote.user.request.UserAuthRequest
import com.mashup.twotoo.datasource.remote.user.request.UserNickNameRequest
import com.mashup.twotoo.datasource.remote.user.response.UserAuthResponse
import com.mashup.twotoo.datasource.remote.user.response.UserInfoResponse
import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel

fun UserAuthRequestDomainModel.toDataModel(): UserAuthRequest {
    return UserAuthRequest(
        socialId = this.socialId,
        loginType = this.loginType,
        deviceToken = this.deviceToken,
    )
}

fun UserAuthResponse.toDomainModel(): UserAuthResponseDomainModel {
    return UserAuthResponseDomainModel(
        socialId = this.socialId,
        loginType = this.loginType,
        deviceToken = this.deviceToken,
        state = this.state,
        accessToken = this.accessToken,
        userNo = this.userNo,
        nickname = this.nickname,
        partnerNo = this.partnerNo,
    )
}

fun UserNickNameDomainModel.toDataModel(): UserNickNameRequest {
    return UserNickNameRequest(
        nickname = this.nickname,
        partnerNo = partnerNo,
    )
}

fun UserInfoResponse.toDomainModel(): UserInfoDomainModel {
    return UserInfoDomainModel(
        userNo = this.userNo,
        nickname = this.nickname,
        partnerNo = this.partnerNo,
    )
}

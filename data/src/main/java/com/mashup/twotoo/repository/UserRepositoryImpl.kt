package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.user.UserDataSource
import com.mashup.twotoo.datasource.remote.user.response.toDomainModel
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.user.PartnerInfoDomainModel
import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel
import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun userAuthorize(userAuthRequestDomainModel: UserAuthRequestDomainModel): NetworkResult<UserAuthResponseDomainModel> {
        return userDataSource.userAuthorize(userAuthRequestDomainModel.toDataModel()).map { userAuthResponse ->
            userAuthResponse.toDomainModel()
        }
    }

    override suspend fun setUserNickName(userNickNameDomainModel: UserNickNameDomainModel): NetworkResult<UserInfoDomainModel> {
        return userDataSource.setUserNickName(userNickNameDomainModel.toDataModel()).map { userInfoResponse ->
            userInfoResponse.toDomainModel()
        }
    }

    override suspend fun getPartnerInfo(): NetworkResult<PartnerInfoDomainModel> {
        return userDataSource.getPartnerInfo().map { partnerInfoResponse ->
            partnerInfoResponse.toDomainModel()
        }
    }

    override suspend fun getUserInfo(): NetworkResult<UserInfoDomainModel> {
        val result = userDataSource.getUserInfo()
        return result.map { userInfoResponse ->
            userInfoResponse.toDomainModel()
        }
    }

    override suspend fun deletePartner(): NetworkResult<Boolean> {
        return userDataSource.deletePartner()
    }

    override suspend fun signOut(): NetworkResult<Boolean> {
        return userDataSource.signOut()
    }

    override suspend fun changeNickname(userNickNameDomainModel: UserNickNameDomainModel): NetworkResult<UserInfoDomainModel> {
        return userDataSource.changeNickname(userNickNameDomainModel.toDataModel()).map { userInfoResponse ->
            userInfoResponse.toDomainModel()
        }
    }
}

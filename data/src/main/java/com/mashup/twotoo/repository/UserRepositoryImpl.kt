package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.user.UserDataSource
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.user.PartnerInfoDomainModel
import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel
import repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun userAuthorize(userAuthRequestDomainModel: UserAuthRequestDomainModel): Result<UserAuthResponseDomainModel> {
        return runCatching {
            userDataSource.userAuthorize(userAuthRequestDomainModel.toDataModel()).toDomainModel()
        }
    }

    override suspend fun setUserNickName(userNickNameDomainModel: UserNickNameDomainModel): Result<UserInfoDomainModel> {
        return runCatching {
            userDataSource.setUserNickName(userNickNameDomainModel.toDataModel()).toDomainModel()
        }
    }

    override suspend fun getPartnerInfo(): Result<PartnerInfoDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): Result<UserInfoDomainModel> {
        TODO("Not yet implemented")
    }
}

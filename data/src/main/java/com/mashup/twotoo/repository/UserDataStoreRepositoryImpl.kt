package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.local.UserPreferenceDataSource
import com.mashup.twotoo.datasource.remote.user.response.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.user.UserAuthResponseDomainModel
import repository.UserDataStoreRepository
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
) : UserDataStoreRepository {
    override suspend fun setUserInfo(userAuthResponseDomainModel: UserAuthResponseDomainModel) {
        userPreferenceDataSource.setUserInfo(userAuthResponseDomainModel.toDataModel())
    }

    override suspend fun getUserInfo(): UserAuthResponseDomainModel {
        return userPreferenceDataSource.getUserInfo()!!.toDomainModel()
    }

    override suspend fun getIsSendInvitation(): Boolean {
        return userPreferenceDataSource.getIsSendInvitation()
    }

    override suspend fun setIsSendInvitation(isSend: Boolean) {
        userPreferenceDataSource.setIsSendInvitation(isSend)
    }

    override suspend fun setVisibilityCheerDialog(visibility: Boolean) {
        userPreferenceDataSource.setVisibilityCheerDialog(visibility = visibility)
    }

    override suspend fun getVisibilityCheerDialog(): Boolean {
        return userPreferenceDataSource.getVisibilityCheerDialog()
    }

    override suspend fun setVisibilityCompleteDialog(visibility: Boolean) {
        userPreferenceDataSource.setVisibilityCompleteDialog(visibility = visibility)
    }

    override suspend fun getVisibilityCompleteDialog(): Boolean {
        return userPreferenceDataSource.getVisibilityCompleteDialog()
    }

    override suspend fun removeVisibilityCheerDialog() {
        userPreferenceDataSource.removeVisibilityCheerDialog()
    }

    override suspend fun removeVisibilityCompleteDialog() {
        userPreferenceDataSource.removeVisibilityCompleteDialog()
    }
}

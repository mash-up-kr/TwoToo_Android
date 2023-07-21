package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.local.UserPreferenceDataSource
import repository.UserDataStoreRepository
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
) : UserDataStoreRepository {
    override suspend fun getAccessToken(): String {
        return userPreferenceDataSource.getAccessToken()
    }

    override suspend fun setAccessToken(accessToken: String) {
        userPreferenceDataSource.setAccessToken(accessToken)
    }

    override suspend fun setUserNo(userNo: Int) {
        userPreferenceDataSource.setUserNo(userNo)
    }

    override suspend fun getUserNo(): Int {
        return userPreferenceDataSource.getUserNo()
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

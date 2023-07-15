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

}

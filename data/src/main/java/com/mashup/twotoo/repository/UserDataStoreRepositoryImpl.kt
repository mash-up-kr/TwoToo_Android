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

    override suspend fun getKaKaoAccessToken(): String {
        return userPreferenceDataSource.getKaKaoAccessToken()
    }

    override suspend fun setKaKaoAccessToken(kaKaoAccessToken: String) {
        userPreferenceDataSource.setKaKaoAccessToken(kaKaoAccessToken)
    }

    override suspend fun getKaKaoRefreshToken(): String {
        return userPreferenceDataSource.getKaKaoRefreshToken()
    }

    override suspend fun setKaKaoRefreshToken(kaKaoRefreshToken: String) {
        userPreferenceDataSource.setKaKaoRefreshToken(kaKaoRefreshToken)
    }
}

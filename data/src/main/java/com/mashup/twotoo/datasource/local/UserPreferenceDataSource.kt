package com.mashup.twotoo.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferenceDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun getAccessToken(): String {
        return getDataStore(stringPreferencesKey(TWOTOO_ACCESS_TOKEN)).first() ?: ""
    }

    suspend fun setAccessToken(accessToken: String) {
        setDataStore(stringPreferencesKey(TWOTOO_ACCESS_TOKEN), accessToken)
    }

    suspend fun getUserNo(): Int {
        return getDataStore(intPreferencesKey(USER_NO)).first() ?: 0
    }

    suspend fun setUserNo(userNo: Int) {
        setDataStore(intPreferencesKey(USER_NO), userNo)
    }

    suspend fun setVisibilityCheerDialog(visibility: Boolean) {
        setDataStore(booleanPreferencesKey(VISIBLE_CHEER_DIALOG), visibility)
    }

    suspend fun getVisibilityCheerDialog(): Boolean {
        return getDataStore(booleanPreferencesKey(VISIBLE_CHEER_DIALOG)).first() ?: false
    }

    suspend fun setVisibilityCompleteDialog(visibility: Boolean) {
        setDataStore(booleanPreferencesKey(VISIBLE_COMPLETE_DIALOG), visibility)
    }

    suspend fun getVisibilityCompleteDialog(): Boolean {
        return getDataStore(booleanPreferencesKey(VISIBLE_COMPLETE_DIALOG)).first() ?: false
    }

    private suspend fun <T> setDataStore(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private fun <T> getDataStore(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[key]
            }
    }

    companion object {
        private const val TWOTOO_ACCESS_TOKEN = "TWOTOO_ACCESS_TOKEN"
        private const val USER_NO = "USER_NO"
        private const val VISIBLE_CHEER_DIALOG = "VISIBLE_CHEER_DIALOG"
        private const val VISIBLE_COMPLETE_DIALOG = "VISIBLE_COMPLETE_DIALOG"
    }
}

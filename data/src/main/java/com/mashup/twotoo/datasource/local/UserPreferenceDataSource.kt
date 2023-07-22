package com.mashup.twotoo.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mashup.twotoo.datasource.remote.user.response.UserAuthResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferenceDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val moshi: Moshi
) {

    suspend fun getIsSendInvitation(): Boolean {
        return getDataStore(booleanPreferencesKey(IS_SEND_INVITATION)).first() ?: false
    }

    suspend fun setIsSendInvitation(isSend: Boolean) {
        setDataStore(booleanPreferencesKey(IS_SEND_INVITATION), isSend)
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

    suspend fun removeVisibilityCheerDialog() {
        dataStore.edit {
            it.remove(booleanPreferencesKey(VISIBLE_CHEER_DIALOG))
        }
    }

    suspend fun removeVisibilityCompleteDialog() {
        dataStore.edit {
            it.remove(booleanPreferencesKey(VISIBLE_COMPLETE_DIALOG))
        }
    }

    suspend fun setUserInfo(userAuthResponse: UserAuthResponse) {
        val json = moshi.adapter(UserAuthResponse::class.java).toJson(userAuthResponse)
        setDataStore(stringPreferencesKey(USER_INFO), json)
    }

    suspend fun getUserInfo(): UserAuthResponse? {
        val jsonAdapter: JsonAdapter<UserAuthResponse> = moshi.adapter(UserAuthResponse::class.java)
        val userInfo = getDataStore(stringPreferencesKey(USER_INFO)).first()
        return jsonAdapter.fromJsonValue(userInfo)
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
        private const val VISIBLE_CHEER_DIALOG = "VISIBLE_CHEER_DIALOG"
        private const val VISIBLE_COMPLETE_DIALOG = "VISIBLE_COMPLETE_DIALOG"
        private const val USER_INFO = "USER_INFO"
        private const val IS_SEND_INVITATION = "IS_SEND_INVITATION"
    }
}

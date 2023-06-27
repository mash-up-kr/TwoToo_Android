package com.mashup.twotoo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPreferenceDataStore(
        context: Context,
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.dataStoreFile(USER_PREFERENCE)
        }

    companion object {
        const val USER_PREFERENCE = "user_preferences.pb"
    }
}

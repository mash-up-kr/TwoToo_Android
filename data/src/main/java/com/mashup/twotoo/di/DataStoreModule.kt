package com.mashup.twotoo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPreferenceDataStore(
        context: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCE)),
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCE) },
        )
    }

    companion object {
        const val USER_PREFERENCE = "user_preferences.pb"
    }
}
